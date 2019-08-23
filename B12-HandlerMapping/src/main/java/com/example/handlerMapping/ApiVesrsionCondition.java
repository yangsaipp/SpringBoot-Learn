package com.example.handlerMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class ApiVesrsionCondition implements RequestCondition<ApiVesrsionCondition> {
 
    // 路径中版本的前缀， 这里用 /v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");
     
    private int apiVersion;
     
    public ApiVesrsionCondition(int apiVersion){
        this.apiVersion = apiVersion;
    }
     
    public ApiVesrsionCondition combine(ApiVesrsionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVesrsionCondition(other.getApiVersion());
    }
    
    // 将请求中的版本号和方法注解上申明版本号进行匹配，为空表示不匹配
    // this对象就是初始化时根据方法上@ApiVesrsion生成的ApiVesrsionCondition对象
    public ApiVesrsionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if(m.find()){
            Integer version = Integer.valueOf(m.group(1));
            if(version >= this.apiVersion) // 如果请求的版本号大于配置版本号， 则满足
                return this;
        }
        return null;
    }
 
    public int compareTo(ApiVesrsionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        return other.getApiVersion() - this.apiVersion;
    }
 
    public int getApiVersion() {
        return apiVersion;
    }
 
}
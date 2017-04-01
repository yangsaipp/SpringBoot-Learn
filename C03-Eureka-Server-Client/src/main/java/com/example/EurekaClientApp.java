package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EurekaClientApp {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
	
	@Autowired
	private EurekaClient eurekaClient;
	
	/**
	 * 根据serverId获取对应的service实例 
	 * @return
	 */
	@RequestMapping("/server1")
    @ResponseBody
	public String serviceUrl() {
		InstanceInfo serverinstance = eurekaClient.getNextServerFromEureka("eurekaserver", false);
		InstanceInfo clientInstance = eurekaClient.getNextServerFromEureka("eurekaClient", false);
		StringBuilder sb = new StringBuilder();
		StringUtil.fieldToString(sb, serverinstance);
		sb.append("<br/>");
		StringUtil.fieldToString(sb, clientInstance);
		
	    return  sb.toString();
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 根据serverId获取对应的service实例 
	 * @return
	 */
	@RequestMapping("/server")
    @ResponseBody
	public Object serviceInfo() {
		List<String> servicesIds = discoveryClient.getServices();
	    List<ServiceInstance> list = discoveryClient.getInstances(servicesIds.get(0));
//	    if (list != null && list.size() > 0 ) {
//	        return StringUtil.getToString(list.get(0)).toString();
//	    }
	    return list;
	}
	
	@RequestMapping("/serverIds")
    @ResponseBody
	public Object servicesIds() {
	    List<String> servicesIds = discoveryClient.getServices();
//	    if (list != null && list.size() > 0 ) {
//	        return StringUtil.getToString(list.get(0)).toString();
//	    }
	    return servicesIds;
	}
	
	public static void main(String[] args) {
		 new SpringApplicationBuilder(EurekaClientApp.class).web(true).run(args);
	}
}

package com.example.two;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

/** 
 * 使用@ComponentScan指定自定义注解，如MyComponent2，那么类上有MyComponent2注解的类将会加入打Spring容器中
 * @author yangsai
 *
 */
@Configuration
@ComponentScan(includeFilters = {
		@Filter(type = FilterType.ANNOTATION, classes = MyComponent2.class) })
public class ScanConfig {

}

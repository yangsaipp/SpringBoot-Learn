package com.comtop.exp.gateway.zuul.errorFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorFilterConfiguration {

//	@Bean
//    public ThrowExceptionProFilter throwExceptionProFilter() {
//        return new ThrowExceptionProFilter();
//    }

	@Bean
	public ThrowExceptionPostFilter throwExceptionPostFilter() {
		return new ThrowExceptionPostFilter();
	}
	
	@Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

	@Bean
	public ErrorExtFilter errorExtFilter() {
		return new ErrorExtFilter();
	}

	@Bean
	public MyBasicFilterUsageNotifier myBasicFilterUsageNotifier() {
		return new MyBasicFilterUsageNotifier();
	}
	
	
}

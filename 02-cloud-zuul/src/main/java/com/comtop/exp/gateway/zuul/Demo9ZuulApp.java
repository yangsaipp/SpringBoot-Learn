package com.comtop.exp.gateway.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZuulProxy
//@EnableAutoConfiguration(exclude={SimpleRouteLocator.class})
public class Demo9ZuulApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Demo9ZuulApp.class, args);
    }
    
    @Autowired
	protected ZuulProperties zuulProperties;

	@Autowired
	protected ServerProperties server;
    
    @Bean
	public RouteLocator routeLocator() {
		return new MyRouteLocator(this.server.getServletPrefix(),
				this.zuulProperties);
	}
}

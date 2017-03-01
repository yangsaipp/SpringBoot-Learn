package com.comtop.exp.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class Demo9ZuulApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Demo9ZuulApp.class, args);
    }
}

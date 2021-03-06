package com.comtop.exp.gateway.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication
@EnableZuulProxy
// @EnableAutoConfiguration(exclude={SimpleRouteLocator.class})
public class Demo9ZuulApp extends SpringBootServletInitializer {
	
	static Logger log = LoggerFactory.getLogger(Demo9ZuulApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Demo9ZuulApp.class, args);
		// 定时5秒后触发SpringContext的RoutesRefreshedEvent
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			public void run() {
//				log.info(" ************* Publish routes refreshed event. ");
//				SpringContextUtil.getApplicationContext().publishEvent(
//						new RoutesRefreshedEvent((RouteLocator) SpringContextUtil.getBean("routeLocator")));
//			}
//		}, 5000);
	}
	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Demo9ZuulApp.class);
    }


	@RequestMapping("/refresh")
    @ResponseBody
    String home() {
		SpringContextUtil.getApplicationContext().publishEvent(
				new RoutesRefreshedEvent(routeLocator()));
    	return "refresh success.";
    }

	@Autowired
	protected ZuulProperties zuulProperties;

	@Autowired
	protected ServerProperties server;

//	@Bean
//	 MyRouteLocator替换自动配置使用的SimpleRouteLocator
	public RouteLocator routeLocator() {
		return new MyRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
	}
	
	/**
	 * @return AuthFilter
	 */
	@Bean
	public AuthFilter authFilter() {
	    return new AuthFilter();
	}
}

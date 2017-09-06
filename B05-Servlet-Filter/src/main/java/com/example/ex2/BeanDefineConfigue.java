package com.example.ex2;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ConfigurableWebApplicationContext;

//ContextRefreshedEvent为初始化完毕事件，spring还有很多事件可以利用
@Component
public class BeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent> {

	/**
	 * 当一个ApplicationContext被初始化或刷新触发
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ConfigurableWebApplicationContext context = (ConfigurableWebApplicationContext) event.getApplicationContext();
		System.out.println("spring容易初始化完毕================================================");
	}

}

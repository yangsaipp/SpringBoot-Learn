package com.example.one;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 利用applicationContext.getBeansWithAnnotation(MyComponent.class)获取所有PrintAble实例的beanName
 * @author yangsai
 *
 */
@Component
public class Register3 implements ApplicationContextAware, InitializingBean{
	
	private Map<String, PrintAble> map = new HashMap<>();
	
	private ApplicationContext applicationContext;
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 所有bean已经初始化成功后（bean已经实例化并设置属性完毕）
		// 获取所有PrintAble实例的beanName
		Map<String, Object> mapBean = applicationContext.getBeansWithAnnotation(MyComponent.class);
		for(Map.Entry<String, Object> entry : mapBean.entrySet()) {
			MyComponent my = entry.getValue().getClass().getAnnotation(MyComponent.class);
			map.put(my.value(), (PrintAble)entry.getValue());
		}
		System.out.println("================register3 ========" + map);
	}
	
}

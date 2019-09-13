package com.example.one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 利用@Autowired注解获取所有 PrintAble实例对象，若一个都没有则会报错，推荐使用2和3
 * @author yangsai
 *
 */
@Component
public class Register {
	
	private Map<String, PrintAble> map = new HashMap<>();
	
	@Autowired
	public Register(List<PrintAble> lstPrintAble) {
		super();
		for(PrintAble p : lstPrintAble) {
			MyComponent myComponent = p.getClass().getAnnotation(MyComponent.class);
			map.put(myComponent.value(), p);
		}
		System.out.println("================register ========" + map);
	}
	
}

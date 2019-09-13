package com.example.two;

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
public class ARegister {
	
	private Map<String, A> map = new HashMap<>();
	
	@Autowired
	public ARegister(List<A> lstPrintAble) {
		super();
		for(A p : lstPrintAble) {
			MyComponent2 myComponent = p.getClass().getAnnotation(MyComponent2.class);
			map.put(myComponent.value(), p);
		}
		System.out.println("*************** Aregister ========" + map);
	}
	
}

package com.example;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoApplication2Tests extends DemoApplicationTests {
	
	@Autowired
	private MyConfig myConfig;
	
	@Test
	public void contextLoads() {
		System.out.println(myConfig);
	}

}

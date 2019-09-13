package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.facade.TestFacadeA;

@Controller
public class TestControllerA {
	
	@Autowired
	private TestFacadeA testFacadeA;
	
	private static int num = 0;

	
	@RequestMapping("/testA")
    @ResponseBody
    String testA() {
		testFacadeA.a(num++);
        return "Hello World!";
    }
}

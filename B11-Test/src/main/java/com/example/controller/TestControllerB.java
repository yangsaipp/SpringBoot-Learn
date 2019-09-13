package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.facade.TestFacadeB;

@Controller
public class TestControllerB {
	
	@Autowired
	private TestFacadeB testFacadeB;

	@RequestMapping("/testB")
    @ResponseBody
    String testB() {
		testFacadeB.b();
        return "Hello World!";
    }
}

package com.example.facade;

import org.springframework.stereotype.Service;

@Service
public class TestFacadeB {

	public void b() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

}

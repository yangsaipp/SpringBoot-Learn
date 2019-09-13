package com.example.facade;

import org.springframework.stereotype.Service;

@Service
public class TestFacadeA {
	

	public void a(int num) {
		System.out.println(num);
		if(num % 10 == 1) {
			a1();
		}else {
			try {	
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void a1() {
		System.out.println("a1-sleep....");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

package com.example.one;

@MyComponent("oracle")
public class Oracle implements PrintAble {
	public void print() {
		System.out.println("hello world, Oracle.");
	}
}
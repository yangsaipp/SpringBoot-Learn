package com.example.two;

@MyComponent2("mysql")
public class A1 implements A {
	/* (non-Javadoc)
	 * @see com.example.one.PrintAble#print()
	 */
	@Override
	public void print() {
		System.out.println("hello world, mysql.");
	}
}
package com.example.one;

@MyComponent("mysql")
public class MySQL implements PrintAble {
	/* (non-Javadoc)
	 * @see com.example.one.PrintAble#print()
	 */
	@Override
	public void print() {
		System.out.println("hello world, mysql.");
	}
}
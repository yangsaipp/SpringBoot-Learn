package com.example.app;


public class Store {
	
	private String storeId;
	
	private String name;

	@Override
	public String toString() {
		return storeId + " : " + name + " : " + "<br/>";
	}

	public Store() {
		super();
	}
	
	public Store(String storeId, String name) {
		super();
		this.storeId = storeId;
		this.name = name;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

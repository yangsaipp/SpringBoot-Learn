package com.example.auth;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AutherRegistry {
	
	private List<IAuther> lstAuther;

	public AutherRegistry(List<IAuther> lstAuther) {
		super();
		this.lstAuther = lstAuther;
		Collections.sort(lstAuther, new Comparator<IAuther>() {

			@Override
			public int compare(IAuther o1, IAuther o2) {
				return Integer.compare(o1.order(), o2.order());
			}
			
		});
	}
	
	public List<IAuther> getAllAuther() {
		return lstAuther;
	}
}

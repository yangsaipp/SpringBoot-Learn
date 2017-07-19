package com.example.model;

import java.util.HashMap;
import java.util.Map;
public class User {

	private String id;
	private String name;
	private String password;
	private String sex;
	
	public static Map<String, User> map = new HashMap<String, User>();
	
	static{
		map.put("1", new User("1","张三","123","1"));
		map.put("2", new User("2","李四","123","1"));
		map.put("3", new User("3","王五","123","1"));
		map.put("4", new User("4","赵六","123","2"));
		map.put("5", new User("5","孙七","123","2"));
		map.put("6", new User("6","钱八","123","2"));
	}
	
	public static User get(String id) {
		return map.get(id);
	}
	
	public static void save(User user) {
		map.put(user.getId(), user);
	}
	
	public static User get(String name, String password) {
		for(Map.Entry<String, User> entry: map.entrySet()) {
			User u = entry.getValue();
			if(u.getName().equals(name) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	public User() {
		super();
	}

	public User(String id, String name, String password, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

}

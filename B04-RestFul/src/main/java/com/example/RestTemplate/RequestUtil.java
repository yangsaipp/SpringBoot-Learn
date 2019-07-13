package com.example.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtil  {
	
	private static final RequestThreadLocal single = new RequestThreadLocal();
	
	public static void setRequest(HttpServletRequest request) {
		single.get().put("request", request);
	}
	
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) single.get().get("request");
	}
	
	public static void setResponse(HttpServletResponse response) {
		single.get().put("response", response);
	}
	
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) single.get().get("response");
	}
	
	public static void remove() {
		single.remove();
	}
	
	static class RequestThreadLocal extends ThreadLocal<Map<String, Object>> {
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>(2);
		}
	}
	
}

package com.example.RestTemplate;

import java.net.URI;

import org.springframework.http.HttpMethod;

public class HttpRequstException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3548719110146043600L;

	public HttpRequstException(URI url, HttpMethod method, Exception e) {
		super(String.format("http请求发生异常。uri=%s,method=%s", url, method), e);
	}
	
	

}

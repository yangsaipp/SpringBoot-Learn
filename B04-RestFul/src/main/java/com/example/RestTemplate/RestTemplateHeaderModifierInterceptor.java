package com.example.RestTemplate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

	/** */
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpServletRequest _request = RequestUtil.getRequest();
		HttpServletResponse _response= RequestUtil.getResponse();
		HttpHeaders headers = request.getHeaders();
//	    headers.add("cookie", "PSTM=1540089777;");
		// 加入请求中的cookie
	    String cookieValue = _request.getHeader(HttpHeaders.COOKIE);
	    System.out.println(cookieValue);
	    headers.add(HttpHeaders.COOKIE, cookieValue);
	    
		ClientHttpResponse response = execution.execute(request, body);
		cookieValue = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
		// 设置响应的cookie
		_response.addHeader(HttpHeaders.SET_COOKIE, cookieValue);
		return response;
	}
}
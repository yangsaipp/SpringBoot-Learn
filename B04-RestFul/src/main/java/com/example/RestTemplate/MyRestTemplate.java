package com.example.RestTemplate;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class MyRestTemplate extends RestTemplate{
	
	/** */
//	private static final Logger logger = LoggerFactory.getLogger(MyRestTemplate.class);
	
	@Override
	public <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException {
		try {
			return super.doExecute(url, method, requestCallback, responseExtractor);
		} catch (Exception e) {
			throw new HttpRequstException(url, method, e);
		}
	}

	public MyRestTemplate() {
		super();
	}

	public MyRestTemplate(ClientHttpRequestFactory requestFactory) {
		super(requestFactory);
	}

	public MyRestTemplate(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}
	
}

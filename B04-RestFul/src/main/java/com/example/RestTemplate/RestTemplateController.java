package com.example.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.RouteConfigVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RestTemplateController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/restTemplate/list")
	@ResponseBody
	public Object list() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("perPage", 1);
		param.put("page", 2);
		@SuppressWarnings("unchecked")
		Map<String,?> routeMap = restTemplate.getForObject("http://localhost:8080/routes?perPage={perPage}&page={page}", Map.class, param);
		System.out.printf("total:%s (%s)\n",routeMap.get("total"), routeMap.get("total").getClass());
		System.out.printf("total:%s (%s)\n",routeMap.get("data"), routeMap.get("data").getClass());
		return routeMap;
	}
	
	@RequestMapping(value="/restTemplate/get")
	@ResponseBody
	public Object get() {
		RouteConfigVO routeConfigVO = restTemplate.getForObject("http://localhost:8080/routes/{id}", RouteConfigVO.class, 1);
		return routeConfigVO;
	}
	
	@RequestMapping(value="/restTemplate/get2")
	public Object get2() {
		// 这种方式得到的是完整的response信息，包括所有的header。
		ResponseEntity<RouteConfigVO> rep = restTemplate.getForEntity("http://localhost:8080/routes/{id}", RouteConfigVO.class, 1);
		System.out.println("contextType: "+ rep.getHeaders().getContentType());
		// 可以不用@ResponseBody注解。
		return rep;
	}
	
	@RequestMapping(value="/restTemplate/post")
	public Object post() {
		// 构建header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");

		// 构建JSON字符串
		RouteConfigVO vo = new RouteConfigVO();
		vo.setServiceName("yangsai测试");
		vo.setUrl("www.baidu.com");
		vo.setServiceId("ys");
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(vo);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
	        
		// 构建Request
		HttpEntity<String> requestEntity = new HttpEntity<String>(jsonStr,headers);

		ResponseEntity<RouteConfigVO> rep = null;
		try {
			rep = restTemplate
					.postForEntity(
							"http://localhost:8080/routes/",
							requestEntity, RouteConfigVO.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return rep;
	}
	
	@RequestMapping(value="/restTemplate/error")
	public Object error() {
		// 请求一个不存在的URL，错误处理
		ResponseEntity<RouteConfigVO> rep = restTemplate.getForEntity("http://localhost:8080/routes2/{id}", RouteConfigVO.class, 1);
		System.out.println("contextType: "+ rep.getHeaders().getContentType());
		// 可以不用@ResponseBody注解。
		return rep;
	}
}

/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市康拓普信息技术有限公司开发研制。未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.handlerMapping.ApiVersion;
import com.example.model.DiscoveryType;
import com.example.model.LoadBalanceStrategy;
import com.example.model.RouteConfigVO;

/**
 * 
 * 网关配置管理工作台controller
 * @author lihuan
 *
 */
//@RestController("/api/{version}/")
@RequestMapping("/{version}/")
@Controller
public class RouteResource {

	private static final ThreadLocal<String> tl = new ThreadLocal<String>();
	/**
	 * 日志
	 */
	static Logger log = LoggerFactory.getLogger(RouteResource.class);
	
	/**
	 * list
	 */
	private Map<String, RouteConfigVO> map;
	
	/**
	 * 
	 * 构造函数
	 */
	public RouteResource() {
		map = new HashMap<String, RouteConfigVO>();
		RouteConfigVO vo = new RouteConfigVO();
		vo.setId("1");
		vo.setServiceName("用户服务");
		vo.setPath("/us/**");
		vo.setStripPrefix(true);
		vo.addSensitiveHeader("Cookie");
		vo.addSensitiveHeader("Set-Cookie");
		vo.addSensitiveHeader("Authorization");
		vo.setDiscoveryType(DiscoveryType.SERVICE_ID);
		vo.setServiceId("user");
		vo.setLoadBalanceStrategy(LoadBalanceStrategy.POLLING);
		vo.setRetryTimes(0);
		map.put(vo.getId(), vo);
		
		vo = new RouteConfigVO();
		vo.setId("2");
		vo.setServiceName("百度服务");
		vo.setPath("/bd/**");
		vo.setStripPrefix(true);
		vo.addSensitiveHeader("Cookie");
		vo.addSensitiveHeader("Set-Cookie");
		vo.addSensitiveHeader("Authorization");
		vo.setDiscoveryType(DiscoveryType.URL);
		vo.setUrl("https://www.baidu.com/");
		vo.setLoadBalanceStrategy(LoadBalanceStrategy.NO);
		vo.setRetryTimes(0);
		
		map.put(vo.getId(), vo);
	}
	
	/**
	 * @param page 页数，默认为第一页
	 * @param perPage 每页数据量，默认每页100条数据 
	 * @return 所有路由服务规则
	 */
	@ApiVersion(1)
	@ResponseBody
	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public Object pageList(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "perPage", defaultValue = "100") int perPage){
		System.out.println("set thead:"+ Thread.currentThread().getName());
		tl.set("routes");
		System.out.println("page:"+ page);
		System.out.println("perPage:"+ perPage);
		Map<String,Object> routes = new HashMap<String,Object>();
		routes.put("total", map.size());
		List<RouteConfigVO> lst = new ArrayList<RouteConfigVO>();
		lst.add(map.get(String.valueOf(page)));
		routes.put("data", map.values());
		return routes;
	}
	
	/**
	 * Get RouteConfigVO
	 * @param id id
	 * @return RouteConfigVO
	 */
	@ResponseBody
	@RequestMapping(value = "/routes/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
		System.out.println("get thead:"+ Thread.currentThread().getName());
		System.out.println("threadLocal: " + tl.get());
		System.out.println(request.getHeader("Foo"));
		if(request.getCookies() == null) {
			return map.get(id); 
		}
		for(Cookie c : request.getCookies()) {
			System.out.println(c.getName() + "=" + c.getValue());
		}
//		response.addCookie(new Cookie("xx","ss"));
		response.addCookie(new Cookie("ss","ss2"));
		Cookie c = new Cookie("ss2","ss2");
		c.setPath("/restTemplate");
//		c.setMaxAge(111111);
		response.addCookie(c);
		return map.get(id);
	}
	
	/**
	 * add RouteConfigVO
	 * @param routeConfigVO routeConfigVO
	 * @return routeConfigVO
	 */
	@ResponseBody
	@RequestMapping(value = "/routes", method = RequestMethod.POST)
	public Object add(@RequestBody RouteConfigVO routeConfigVO){
		routeConfigVO.setId(String.valueOf((new Random().nextInt())));
		log.info("add routeConfigVO : {}", routeConfigVO.getServiceName());
		map.put(routeConfigVO.getId(), routeConfigVO);
		return routeConfigVO;
	}
	
	/**
	 * add RouteConfigVO
	 * @param id id
	 * @param routeConfigVO routeConfigVO
	 * @return routeConfigVO
	 */
	@RequestMapping(value = "/routes/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable String id, @RequestBody RouteConfigVO routeConfigVO){
		map.put(routeConfigVO.getId(), routeConfigVO);
		log.info("update routeConfigVO : {}",routeConfigVO.getServiceName());
		return routeConfigVO;
	}
	
	
	/**
	 * Delete RouteConfigVO
	 * @param id id
	 * @return RouteConfigVO
	 */
	@RequestMapping(value = "/routes/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable String id){
		RouteConfigVO vo = new RouteConfigVO();
		log.info("delete routeConfigVO id : {}",id);
		return vo;
	}
}


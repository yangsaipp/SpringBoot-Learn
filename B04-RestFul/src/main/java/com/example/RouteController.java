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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.DiscoveryType;
import com.example.model.LoadBalanceStrategy;
import com.example.model.RouteConfigVO;

/**
 * 
 * 网关配置管理工作台controller
 * @author lihuan
 *
 */
@RestController
public class RouteController {

	/**
	 * 日志
	 */
	static Logger log = LoggerFactory.getLogger(RouteController.class);
	
	/**
	 * list
	 */
	private Map<String, RouteConfigVO> map;
	
	/**
	 * 
	 * 构造函数
	 */
	public RouteController() {
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
	@RequestMapping(value = "/console/routes", method = RequestMethod.GET)
	public Object routes(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "perPage", defaultValue = "100") int perPage){
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
	@RequestMapping(value = "/console/routes/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable String id){
		return map.get(id);
	}
	
	/**
	 * add RouteConfigVO
	 * @param routeConfigVO routeConfigVO
	 * @return routeConfigVO
	 */
	@RequestMapping(value = "/console/routes", method = RequestMethod.POST)
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
	@RequestMapping(value = "/console/routes/{id}", method = RequestMethod.PUT)
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
	@RequestMapping(value = "/console/routes/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable String id){
		RouteConfigVO vo = new RouteConfigVO();
		log.info("delete routeConfigVO id : {}",id);
		return vo;
	}
}

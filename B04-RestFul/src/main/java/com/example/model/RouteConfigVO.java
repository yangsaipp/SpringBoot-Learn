/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市康拓普信息技术有限公司开发研制。未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.example.model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 服务路由配置对象
 * 
 * @author lihuan
 *
 */
public class RouteConfigVO {
	
	public String id;
	
	/**
	 * 
	 */
	public RouteConfigVO() {}
	
	/**
	 * 路由规则服务名
	 */
	private String serviceName;
	
	/**
	 * 网关请求路径，如/user/**
	 */
	private String path;

	/**
	 * 当路由服务发现策略为URL时所调用服务的URL
	 */
	private String url;
	
	/**
	 * 当路由服务发现策略为SERVICEID时所调用服务的serverId
	 */
	private String serviceId;
	
	/**
	 * 是否忽略前缀
	 */
	private boolean stripPrefix;

	/**
	 * 敏感信息头
	 */
	private Set<String> sensitiveHeaders = new LinkedHashSet<>();
	

	/** 要路由的服务发现方式 */
	private DiscoveryType discoveryType;
	
	/** 路由服务负载策略 */
	private LoadBalanceStrategy loadBalanceStrategy;

	/**
	 * 重试次数，0 表示不重试
	 */
	private int retryTimes = 0;
	
	/**
	 * 配置路由的服务里忽略的请求Path
	 */
	private String ignoreServicePaths;
	
	/**
	 * 配置路由的服务里允许的请求Path
	 */
	private String allowServicePaths;
	/**
	 * @return serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName serviceName
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return stripPrefix
	 */
	public boolean isStripPrefix() {
		return stripPrefix;
	}

	/**
	 * @param stripPrefix stripPrefix
	 */
	public void setStripPrefix(boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
	}

	/**
	 * @return 获取 serviceId属性值
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId 设置 serviceId 属性值为参数值 serviceId
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return 获取 sensitiveHeaders属性值
	 */
	public Set<String> getSensitiveHeaders() {
		return sensitiveHeaders;
	}

	/**
	 * @param sensitiveHeaders 设置 sensitiveHeaders 属性值为参数值 sensitiveHeaders
	 */
	public void setSensitiveHeaders(Set<String> sensitiveHeaders) {
		this.sensitiveHeaders = sensitiveHeaders;
	}

	/**
	 * @param sensitiveHeader sensitiveHeader
	 * @return true 成功 false 失败
	 */
	public boolean addSensitiveHeader(String sensitiveHeader) {
		return this.sensitiveHeaders.add(sensitiveHeader);
	}
	
	/**
	 * @return 获取 discoveryType属性值
	 */
	public DiscoveryType getDiscoveryType() {
		return discoveryType;
	}

	/**
	 * @param discoveryType 设置 discoveryType 属性值为参数值 discoveryType
	 */
	public void setDiscoveryType(DiscoveryType discoveryType) {
		this.discoveryType = discoveryType;
	}

	/**
	 * @return loadBalanceStrategy
	 */
	public LoadBalanceStrategy getLoadBalanceStrategy() {
		return loadBalanceStrategy;
	}

	/**
	 * @param loadBalanceStrategy 负载均衡策略
	 */
	public void setLoadBalanceStrategy(LoadBalanceStrategy loadBalanceStrategy) {
		this.loadBalanceStrategy = loadBalanceStrategy;
	}

	/**
	 * @return 重试次数
	 */
	public int getRetryTimes() {
		return retryTimes;
	}

	/**
	 * @param retryTimes 重试次数
	 */
	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	/**
	 * @return 路由的服务里忽略的请求Path 
	 */
	public String getIgnoreServicePaths() {
		return ignoreServicePaths;
	}

	/**
	 * @param ignoreServicePaths 路由的服务里忽略的请求Path
	 */
	public void setIgnoreServicePaths(String ignoreServicePaths) {
		this.ignoreServicePaths = ignoreServicePaths;
	}

	/**
	 * @return 配置路由的服务里允许的请求Path
	 */
	public String getAllowServicePaths() {
		return allowServicePaths;
	}

	/**
	 * @param allowServicePaths 配置路由的服务里允许的请求Path
	 */
	public void setAllowServicePaths(String allowServicePaths) {
		this.allowServicePaths = allowServicePaths;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

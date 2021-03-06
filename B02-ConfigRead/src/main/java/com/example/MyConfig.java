package com.example;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 注意点：
 * 1. 属性要有对应set方法且必须为public
 * 2. List默认使用ArrayList，若不想使用可通过初始化成员变量的方法更改。
 * @author yangsai
 *
 */
@Configuration
@ConfigurationProperties(prefix="my.config")
public class MyConfig {
	
	@NotNull
	private String name;
	@NotNull(message="port不能为空")
	private Integer port;
	
//	private List<String> servers = new LinkedList<String>();
	private List<String> servers;
	
	@Override
	public String toString() {
		return "MyConfig [name=" + name + ", port=" + port + ", servers="
				+ servers + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}
}

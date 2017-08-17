package com.example.auth.fourA;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="auth.4a")
public class FourAConfig {
	private boolean enabled = false;
	
	@NotNull
	private String serverUrl;
	
	private int order = 0;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
	}
	
	
}

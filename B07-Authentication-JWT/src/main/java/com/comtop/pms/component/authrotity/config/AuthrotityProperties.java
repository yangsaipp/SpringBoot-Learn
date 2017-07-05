package com.comtop.pms.component.authrotity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihuan
 *
 */
@Component
@ConfigurationProperties(prefix = "authrotity")
public class AuthrotityProperties {
	
	
	/** token有效时长（分钟），默认值为5分钟 */
    private int effectiveMinute;
	
	/** 排除权限过滤关键字*/
    private String excludeKeywords;

	/**
	 * @return the effectiveMinute
	 */
	public int getEffectiveMinute() {
		return effectiveMinute;
	}

	/**
	 * @param effectiveMinute the effectiveMinute to set
	 */
	public void setEffectiveMinute(int effectiveMinute) {
		this.effectiveMinute = effectiveMinute;
	}

	/**
	 * @return the excludeKeywords
	 */
	public String getExcludeKeywords() {
		return excludeKeywords;
	}

	/**
	 * @param excludeKeywords the excludeKeywords to set
	 */
	public void setExcludeKeywords(String excludeKeywords) {
		this.excludeKeywords = excludeKeywords;
	}
    
}

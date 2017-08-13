package com.example.auth.fourA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ FourAConfig.class })
public class FourAConfiguration {
	
	@Autowired
	private FourAConfig fourAConfig;
	
	@Bean
	@ConditionalOnProperty(prefix="auth.4a",name="enabled")	//当auth.4a.enable=true才构建FourAAuther这个bean
	public FourAAuther fourAAuther() {
		return new FourAAuther(fourAConfig);
	}
}

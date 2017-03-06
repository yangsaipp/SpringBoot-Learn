package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApp {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
	
	@Autowired
	private EurekaClient discoveryClient;
	
	@RequestMapping("/serverUrl")
    @ResponseBody
	public String serviceUrl() {
		List<InstanceInfo> list = discoveryClient.getInstancesById("EUREKACLIENT");
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getHomePageUrl();
	    }
	    return null;
	}
	
	/**
	 * @param id
	 * @return 1
	 */
//	@GetMapping("/user/{id}")
//	public User findById(@PathVariable Long id){
//		return userRepository.findOne(id);
//	}
	
	public static void main(String[] args) {
		 new SpringApplicationBuilder(EurekaClientApp.class).web(true).run(args);
	}
}

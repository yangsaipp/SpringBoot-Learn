package com.example.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/store")
public class StoreControl {
	
	@Autowired
	private StoreClient storeClient;
	
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @ResponseBody
    List<Store> getStores() {
    	return storeClient.getStores();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/update")
    @ResponseBody
    Store update() {
    	return storeClient.update("2", new Store("2", "kfc"));
    }
}
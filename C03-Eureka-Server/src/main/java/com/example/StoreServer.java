package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreServer {
    
	@RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Store> getStores() {
    	List<Store> storeList = new ArrayList<Store>();
    	storeList.add(new Store("1", "KFC"));
    	storeList.add(new Store("2", "MDL"));
    	return storeList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") String storeId, Store store) {
    	store.setName(store.getName() + "(update)");
    	return store;
    }
}
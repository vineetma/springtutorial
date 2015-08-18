package com.dakinegroup.storesApp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dakinegroup.storesApp.store.Store;

@RestController
public class StoreMasterController {

    @RequestMapping(value="/v1/master/stores",method=RequestMethod.POST)
    public Store create() {
    	return null;
    }
    
    @RequestMapping(value="/v1/master/stores/{id}", method=RequestMethod.DELETE)
    public Object remove(@PathVariable(value="id") String id) {
    	return new Object() {
    		@SuppressWarnings("unused")
			String message="Got Remove Store request";
    	};
    }

    @RequestMapping(value="/v1/master/stores/{id}", method=RequestMethod.PUT)
    public Object update(@PathVariable(value="id") String id) {
    	return new Object() {
    		@SuppressWarnings("unused")
			String message="Got Update Store request";
    	};
    }

    @RequestMapping(value="/v1/master/stores/{id}", method=RequestMethod.GET)
    public Object getList() {
    	return new Object() {
    		@SuppressWarnings("unused")
			String message="Got list Store request";
    	};
    }

}

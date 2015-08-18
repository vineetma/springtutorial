package com.dakinegroup.storesApp.controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dakinegroup.storesApp.store.Store;

@RestController
@RequestMapping("/v1")
public class StoreItemMasterController {

    @RequestMapping(value="/master/items", method=RequestMethod.POST)
    public Store create() {
	Store st = new Store("1");
 	st.displayInfo();
        return st;
    }
    
    @RequestMapping(value="/master/items/{id}", method=RequestMethod.DELETE)
    public Store remove(@PathVariable(value="id") String id) {
    	Store st = new Store("4");
     	st.displayInfo();
            return st;
    }

    @RequestMapping(value="/master/items/{id}", method=RequestMethod.PUT)
    public String update(@PathVariable(value="id") String id) {
    	return "Updating..";
    }

}

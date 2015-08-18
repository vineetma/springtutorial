package com.dakinegroup.storesApp.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dakinegroup.storesApp.store.Store;

@RestController
public class StoreItemMasterController {

    @RequestMapping("/v1/master/item/create")
    public Store create(@RequestParam(value="id", defaultValue="NA") String id) {
	Store st = new Store(id);
 	st.displayInfo();
        return st;
    }
    
    @RequestMapping("/v1/master/item/remove")
    public Store remove(@RequestParam(value="id", defaultValue="NA") String id) {
	Store st = new Store(id);
 	st.displayInfo();
        return st;
    }

    @RequestMapping("/v1/master/item/update")
    public Store update(@RequestParam(value="id", defaultValue="NA") String id) {
	Store st = new Store(id);
 	st.displayInfo();
        return st;
    }

}

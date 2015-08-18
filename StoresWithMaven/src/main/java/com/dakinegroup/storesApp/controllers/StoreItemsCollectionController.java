package com.dakinegroup.storesApp.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dakinegroup.storesApp.store.Store;

@RestController
public class StoreItemsCollectionController {

	// it adds quantity of a particular item to the store
	// .. if the item does not exist in store, than this entry
	// .. is created and than quantity updated
    @RequestMapping(value="/v1/stores/{id}/item/{id2}",method=RequestMethod.POST)
    public Object add(@PathVariable(value="id") String id,
    		@PathVariable(value="id2") String id2) {
    	return new Object() {
    		@SuppressWarnings("unused")
			public String message="Got add item to store request";
    	};
    }

    // it retrieves status of a particular item. it will return following details
    //  + balance available
    //  + last date of update
    @RequestMapping(value="/v1/stores/{id}/item/{id2}",method=RequestMethod.GET)
    public Object getOne(@PathVariable(value="id") String id,
    		@PathVariable(value="id2") String id2) {
    	return new Object() {
    		@SuppressWarnings("unused")
			public String message="Got list item from store";
    	};
    }

    // it retrieves all items in a given store with ``id``
    @RequestMapping(value="/v1/stores/{id}",method=RequestMethod.GET)
    public Object getAll(@PathVariable(value="id") String id) {
    	return new Object() {
    		@SuppressWarnings("unused")
			public String message="Got list all item from store";
    	};
    }

    //TODO: from where do we get the quantity
    // it removes particular qty of item from stores
    @RequestMapping(value="/v1/stores/{id}/item/{id2}",method=RequestMethod.DELETE)
    public Object remove(@PathVariable(value="id") String id,
    		@PathVariable(value="id2") String id2) {
    	return new Object() {
    		  @SuppressWarnings("unused")
			public String message = "Got delete request"; 
    		};
    	
    }

    //TODO: from where do we get the stock details
    // it updates stock status for an item in store
    @RequestMapping(value="/v1/stores/{id}/item/{id2}",method=RequestMethod.PUT)
    public Object update(@PathVariable(value="id") String id,
    		@PathVariable(value="id2") String id2) {
    	return new Object() {
    		  @SuppressWarnings("unused")
			public String message = "Got updaterequest"; 
    		};
    	
    }

}

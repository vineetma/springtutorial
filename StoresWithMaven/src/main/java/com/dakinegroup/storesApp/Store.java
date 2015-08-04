package com.dakinegroup.storesApp;

import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Store {
    private List<StoreItem> items;
    private String storeId;
    private String manager;
    private String location;
    private static final Logger logger = LogManager.getLogger(StoreItem.class);
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public List<StoreItem> getItems() {
        return items;
    }
    public void setItems(List<StoreItem> items) {
        this.items = items;
    }
    
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Store() {
        
    }
    public Store(String id) {
        this.storeId = id;
    }
    public Store(String id, List<StoreItem> items) {
        this.storeId = id;
        this.items = items;
    }
    public void displayInfo() {
        logger.trace("Store Id: "+this.storeId);
        if(items != null) {
        Iterator<StoreItem> it = items.iterator();
        while(it.hasNext()) {
            it.next().displayInfo();
        }
       }
    }
    public void addItem(StoreItem item) {
        System.out.println("Store: addItem: " + item.getErpcode());
        items.add(item);
        //TODO: Check if this item already exists, if yes, than update other parameters
    }
    public void removeItem(String erp) {
        boolean found = false;
        System.out.println("Store: removeItem: "+ erp);
        Iterator<StoreItem> it = items.iterator();
        while(it.hasNext()) {
            StoreItem i = it.next();
            if(i.getErpcode() == erp) {
                it.remove();
                found = true;
                System.out.println("Found and removed: ERP: " + erp);
                break;
            }
        }
        if(!found) {
            System.out.println("Did not find: ERP: " + erp + ". Therefore could not remove.");
        }
    }
}

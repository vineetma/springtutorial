package com.dakinegroup.storesApp.store;

import java.util.Date;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class StoreItem {
 private String description1;
 private String description2;
 private String erpcode;
 private Date dtCreated;
private static final Logger logger = LogManager.getLogger(StoreItem.class);
 
 // getters and setters
 public String getDescription1() {
     return description1;
 }
 public void setDescription1(String description) {
     this.description1 = description;
 }
 public String getDescription2() {
    return description2;
}
public void setDescription2(String description2) {
    this.description2 = description2;
}
public String getErpcode() {
    return erpcode;
}
public void setErpcode(String erpcode) {
    this.erpcode = erpcode;
}
public Date getDtCreated() {
    return dtCreated;
}
public void setDtCreated(Date dtCreated) {
    this.dtCreated = dtCreated;
}
// Constructors
public StoreItem(String erpcode, String description) {
    this.erpcode = erpcode;
    this.description1 = description;
    this.description2="";
}
public StoreItem(String erpcode) {
    this.erpcode = erpcode;
    this.description1="Default";
    this.description2="Default";
}
public StoreItem() {
    this.erpcode = "Invalid";
    this.description1="Invalid";
    this.description2="Invalid";
}

// Service methods
void displayInfo() {
     System.out.println("Item: " );
     System.out.println(" .. ERP Code: " + getErpcode());
     System.out.println(" .. Description: " + getDescription1());
     System.out.println(" .. Description: " + getDescription2());    
     logger.trace("Item: " );
     logger.trace(" .. ERP Code: " + getErpcode());
     logger.trace(" .. Description: " + getDescription1());
     logger.trace(" .. Description: " + getDescription2());    
}

}

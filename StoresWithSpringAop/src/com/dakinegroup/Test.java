package com.dakinegroup;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Test {
    public static void main(String[] args) {
//        BasicConfigurator.configure();
        System.out.println("Hello World");
        StoreItem si = new StoreItem();
        si.setErpcode("E32334");
        si.setDescription1("Shirt XL Cotton - Red - Arrow");
        si.setDescription2("Shirt L Cotton - Red - Arrow");
        si.displayInfo();
    }
}

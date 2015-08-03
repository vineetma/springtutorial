package com.dakinegroup;


public class Test {
    public static void main(String[] args) {
        System.out.println("Hello World");
        StoreItem si = new StoreItem();
        si.setErpcode("E32334");
        si.setDescription1("Shirt XL Cotton - Red - Arrow");
        si.setDescription2("Shirt L Cotton - Red - Arrow");
        si.displayInfo();
    }
}

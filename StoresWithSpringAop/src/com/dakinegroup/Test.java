package com.dakinegroup;
import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;  
  
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
	Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        
        Store si2 = (Store)factory.getBean("store1");
        si2.displayInfo();
    }
}

---
layout: docs
overview: true
title: Add spring
---

Source of this code is available from [here](https://github.com/vineetma/springtutorial/tree/dcab41e13d44dfe609138d0a4b8bdcc891a8389e)

Spring has evolved significantly. We will start here from the basics, for which Spring used to be known earlier. Today, it has several integrations and extensive of annotations that make whole development much easier. Idea is to understand what is going behind the scene. We will come to latest features of Spring later as we start to use maven.

In this example, we will create store using context file with two items. Note that we will not write any java code to do so. All of it is taken care by spring framework, using dependency injections.

#Get spring jar files
Download latest spring jar files (here [4.2.0](http://maven.springframework.org/release/org/springframework/spring/4.2.0.RELEASE/) is used) from [here](http://maven.springframework.org/release/org/springframework/spring/).

Unzip and place these jar files directory in the lib directory
of our project.

Spring framework also uses LogFactory from commons-logging. So that also requires to be downloaded. Get it from [here](http://commons.apache.org/proper/commons-logging/download_logging.cgi)

#Understanding Dependency Injection
Simply put, instead of writing code to instantiate object and make it available for use with other object, we write an xml, initialize it there and connect the objects with each other. By doing so, we are able to avoid changes to code for any change in relationships between the objects and their initial states. More on dependency injections, see [here](lets_do_constructor_injection.html).

This file is called, applicationContext.xml.

In our example of Stores, for example, we want to create a store, 2 different items and link them to the store. Instead of writing the code for all of this, we shall simply fetch bean of stores which will have these two different items in it, initialized through applicationContext.

#applicationContext.xml

{% highlight xml %}
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:mvc="http://www.springframework.org/schema/mvc"
        xsi:schemaLocation="
        http://www.springframework.org/schema/beans     
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc 
                http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <bean id="storeitem1" class="com.dakinegroup.StoreItem">
       <property name="erpcode" value="05230419"></property>
        <property name="description1" value="Steel Plate"></property>
       <property name="description2" value="Galvanised Hammered Steel"></property>
    </bean>
    <bean id="storeitem2" class="com.dakinegroup.StoreItem">
       <property name="erpcode" value="0229003"></property>
    </bean>
    <bean id="store1" class="com.dakinegroup.Store">
        <property name="items">
            <list>
                <ref bean="storeitem1" />
                <ref bean="storeitem2" />
            </list>
        </property>
    </bean>

</beans>
{% endhighlight %}

#Changes to build.xml

Add line to copy task for applicationContext.xml

{% highlight xml %}
  <copy todir="${classes.dir}">
   <resources>
     <file file="${src.dir}/log4j2.xml" />
     <file file="${src.dir}/applicationContext.xml" />
   </resources>
  </copy>
{% endhighlight %}

#Changes to Test to invoke Setter Injection

Add following code towards the end of main function.

{% highlight java %}

       Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);

        Store si2 = (Store)factory.getBean("store1");
        si2.displayInfo();

{% endhighlight %}

#Run
Add following code towards the end of main function.

{% highlight java %}
...
     [java] Aug 04, 2015 10:40:56 AM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
     [java] INFO: Loading XML bean definitions from class path resource [applicationContext.xml]
     [java] Item: 
     [java]  .. ERP Code: 05230419
     [java]  .. Description: Steel Plate
     [java]  .. Description: Galvanised Hammered Steel
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem - Item: 
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem -  .. ERP Code: 05230419
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Steel Plate
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Galvanised Hammered Steel
     [java] Item: 
     [java]  .. ERP Code: 0229003
     [java]  .. Description: Default
     [java]  .. Description: Default
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem - Item: 
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem -  .. ERP Code: 0229003
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Default
     [java] 10:40:57.025 [main] TRACE com.dakinegroup.StoreItem -  .. Description

{% endhighlight %}

[Back to home](index.html)

---
layout: docs
overview: true
title: Dependency Injections
---

In last section we saw, that through applicationContext.xml, we could configure new instances of items, store and link them with each other. We made use of setter constructor injection and definition of properties including of type "list".

#Constructor Injection

Here lets go over another important aspect of creating instance using constructor. In previous case, we had used empty constructor, implicitly called as part of instantiation.

{% highlight xml %}
...
<bean id="storeitem3" class="com.dakinegroup.StoreItem">
        <constructor-arg value="0521003" type="String"></constructor-arg>
        <constructor-arg value="Bearings - steel" type="String"></constructor-arg>
</bean>
...
<bean id="store1" class="com.dakinegroup.Store">
        <property name="items">
            <list>
                ...
                <ref bean="storeitem3" />
                ...

{% endhighlight %}


This shall instantiate third item in the store. When we run, the code, we shall see following output.

{% highlight bash %}
  [java] 22:49:39.960 [main] TRACE com.dakinegroup.StoreItem -  .. ERP Code: 05230419
     [java] 22:49:39.960 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Steel Plate
     [java] 22:49:39.960 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Galvanised Hammered Steel
     [java] Item: 
     [java]  .. ERP Code: 0229003
     [java]  .. Description: Invalid
     [java]  .. Description: Invalid
     [java] 22:49:39.960 [main] TRACE com.dakinegroup.StoreItem - Item: 
     [java] 22:49:39.960 [main] TRACE com.dakinegroup.StoreItem -  .. ERP Code: 0229003
     [java] 22:49:39.961 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Invalid
     [java] 22:49:39.961 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Invalid
     [java] Item: 
     [java]  .. ERP Code: 0521003
     [java]  .. Description: Bearings - steel
     [java]  .. Description: 
     [java] 22:49:39.961 [main] TRACE com.dakinegroup.StoreItem - Item: 
     [java] 22:49:39.961 [main] TRACE com.dakinegroup.StoreItem -  .. ERP Code: 0521003
     [java] 22:49:39.961 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Bearings - steel
     [java] 22:49:39.961 [main] TRACE com.dakinegroup.StoreItem -  .. Description: 
{% endhighlight %}

#Beans
Lets go a bit back and grab what we saw bean to be in the code. It is a living object given birth through applicationContext. It is an instantiated version of a plain java object. Application context, identifies it with a name and use that as variable name to link between various beans as reference/member/association.


#Setter Injection
We have already seen in previous section this part. Will get more clarity as project evolves.

#Using references

We saw, use of references to other beans by use of keyword "ref" followed by id of the bean which is to be made part of the destination object.

#Using collections, maps

To define properties, primitives of type "String", "Integer" and others are used. However, spring allows to use collections (i.e. only list/array of particular primitive types) or maps (i.e. list/array of map/key pair)

We have used list example already, lets see an example (no change to the applicationContext.xml) for reference sake.

{% highlight xml %}
    <property name = storeManagers>
       <map>
         <entry key="store1" value="R. Venkat" />
         <entry key="store2" value="Vishal K." />
       </map>
    </property>
{% endhighlight %}

Corresponding code in the bean has to be like this

{% highlight xml %}
...
   Map<String, String> storeManagers;
...
{% endhighlight %}

[Back to home](index.html)

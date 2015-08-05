---
layout: docs
overview: true
title: Adding Aspects
---

Often while coding, I mean when we have lot of code, we come across situations where we need to make similar code addition to all of it. Also, we may want to do that temporary. Sometimes, we want it to be configurable, i.e. we want to remove it and add it at any time.

Earlier such situations used to be handled through "#ifdef" type of macros in "C", Configurable system flags and regular if then else code with such generic code captured to functions or separate classes/methods and functions.

This used to be serious pain for systems development and maintenance. AoP makes this task easier by providing supoort for coding such concerns that cut across the application.

Such triggers in the code are called advice. Triggers occur at a join point, which basically implies - code block execution, exception, accessing a field.

#Commonly used Aspects

To start with at this point, lets consider following aspects:

+ before a join point (e.g. execution of a code block)
+ after a join point (e.g. after execution of a code block is completed)
+ after throwing advice (e.g. if during the execution of a code block an exception is triggered)
+ after a finally advice (e.g. when the code reaches the final code block)
+ during a function is executed ( at both places before and after join point e.g. before and after execution of a code block)

##Pointcuts
Above only defines possible advice. However, the join points are to be identified. To do so, AoP uses a language (regular expression etc.) and such definitions to identify join points is called "pointcuts".

Checkout list of pointcuts as defined by standards from [here](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html)

##Target and AoP proxy
It is the target object on which the advice is to apply. AoP proxy object is linked to this object. AoP does not work directly with system objects, they work through proxy objects. A proxy object shall contain multiple interceptors that apply to same target.

##Aspect
It is the AoP class where we define join points, advices.

##Interceptor
Only limited to one advice.?? Difference with Aspect.

#Example

Make following additions to applicationContext.xml. 

{% highlight xml %}
    <bean id="doBeforeMethodBean"
            class="com.dakinegroup.aop.DoBeforeMethod" />
    <bean id="doAfterMethodBean"
        class="com.dakinegroup.aop.DoAfterMethod" />
    <bean id="doAroundMethodBean"
        class="com.dakinegroup.aop.DoAroundMethod" />

    <bean id="store1Proxy"
        class="org.springframework.aop.framework.ProxyFactoryBean">
        <property name="target" ref="store1" />
        <property name="interceptorNames">
            <list>
                <value>doBeforeMethodBean</value>
                <value>doAfterMethodBean</value>
                <value>doAroundMethodBean</value>
            </list>
        </property>       
    </bean>       

{% endhighlight %}

What it says is following:

+ create a proxy for store1 (target)
+ provide 3 interceptors "doBefore/After/AroundMethodBean" that are invoked before/after/around the methods of "store1" bean

How this interceptor is implemented?

Each of the interceptors are a class. For sake of clean code, we maintain aop code separately i.e. com.dakinegroup.aop package.

Here is the code for the three:
{% highlight xml %}
package com.dakinegroup.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class DoBeforeMethod implements MethodBeforeAdvice {

    @Override
    public void before(Method arg0, Object[] arg1, Object arg2)
            throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("***SPRINT AOP: DoBeforeMethod: Executing before method");
    }

}
{% endhighlight %}

{% highlight xml %}
package com.dakinegroup.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class DoAfterMethod implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object arg0, Method arg1, Object[] arg2,
            Object arg3) throws Throwable {
        System.out.println("Sprint AOP*****: After the method is called");      
    }

}
{% endhighlight %}
{% highlight xml %}
package com.dakinegroup.aop;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DoAroundMethod implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("****SPRING AOP****: DoAroundMethod: Method Name"+
                    methodInvocation.getMethod().getName());
        System.out.println("****SPRING AOP****: DAM Args: " + 
                    Arrays.toString(methodInvocation.getArguments()));
        System.out.println("****SPRING AOP***: DAM: Executing before..");
        
        try{
            Object result = methodInvocation.proceed();
            System.out.println("****SPRING AOP****: DAM: After method invocation");
            return result;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
{% endhighlight %}

When you run this code, following output is self-explanatory:

We need to download [aop alliance jar]("http://mvnrepository.com/artifact/aopalliance/aopalliance/1.0") and put it under lib directory.

Further, to activate store1proxy, we need to add small code to the Test.java
{% highlight xml %}
....
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
...
...
ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Store store1 = (Store) context.getBean("store1Proxy");
        store1.addItem(new StoreItem("A333245"));
        store1.displayInfo();
        System.out.println("--------------");
....        
{% endhighlight %}
Note here, instead of creating an instance of Store, we are creating instance of AoPProxy done in applicatoinContext and returned as store1Proxy bean. This casted to Store1 and further used. Now Store instance shall have all advices hooked up as per applicationContext.xml


{% highlight xml %}
....
   [java] ***SPRINT AOP: DoBeforeMethod: Executing before method
     [java] ****SPRING AOP****: DoAroundMethod: Method NameaddItem
     [java] ****SPRING AOP****: DAM Args: [com.dakinegroup.StoreItem@7849bbf7]
     [java] ****SPRING AOP***: DAM: Executing before..
     [java] Store: addItem: A333245
     [java] ****SPRING AOP****: DAM: After method invocation
     [java] Sprint AOP*****: After the method is called
     [java] ***SPRINT AOP: DoBeforeMethod: Executing before method
     [java] ****SPRING AOP****: DoAroundMethod: Method NamedisplayInfo
     [java] ****SPRING AOP****: DAM Args: []
     [java] ***SPRING AOP***: DAM: Executing before..
     [java] Item: 
....
{% endhighlight %}

Source code till this point can be accessed [here](https://github.com/vineetma/springtutorial/tree/65b12079f7ac2769592d8e5d63e1d6eb3672923d)


[Back to home](index.html)

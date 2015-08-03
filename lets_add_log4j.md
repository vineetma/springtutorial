---
layout: docs
overview: true
title: Add log4j support
---
We are left with two things to finish our basic layout of stores application (i mean no functionality still). 

+ adding log4j -- to provide logging functionality to our code
+ adding junit -- to get us started on test driven development as well as automated unit testing jigs.

Source code for log4j implementation in our code is available [here](https://github.com/vineetma/springtutorial/tree/7fafc66b82b8e861870f941b4e7515df6449d6d6).

#Where to get these libraries?

You can download log4j   relevant libraries from [here](https://logging.apache.org/log4j/1.2/download.html)

#Properties file for log4j2.xml
Create log4j2.xml file in src directory:

{% highlight xml %}
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
      <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
          <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
      </Appenders>
      <Loggers>
        <Root level="TRACE">
          <AppenderRef ref="Console"/>
        </Root>
      </Loggers>
</Configuration>
{% endhighlight %}

#Modifications to ant file
We already have library path, we just have to save the library to that directory. We should be done with that. Wait!. We need to copy properties file to classes directory and package it in jar. Lets see how is it to be done:

{% highlight xml %}
<target name="compile">
  <javac includeantruntime="false" srcdir="${src.dir}" 
          destdir="${classes.dir}"
          classpathref="classpath" />
  <copy todir="${classes.dir}">
     <fileset dir="${src.dir}" includes="log4j.properties" />
  </copy>
</target>
.....
<target name="run" depends="jar">
  <java fork="true" classname="com.dakinegroup.Test">
    <classpath>
     <path refid="classpath" />
     <path refid="application" />
    </classpath>
  </java>
</target>

{% endhighlight %}

Jar already includes everything under the directory classes. So no change there.

Small enhancements required in run target.

+ fork="true" attribute. allows program to start as new process/thread.
+ add classpath of ``lib`` directory to find log4j, while keeping the applicatino jar as such. So we added classpath collection here.

#Use within the code

Changes to Test.java (though logger is not being used.. it is optional, will be required later.)
{% highlight bash %}
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
{% endhighlight %}

Changes to StoreItem.java
{% highlight bash %}
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
...
public class StoreItem {
...
private Date dtCreated;
private static final Logger logger = LogManager.getLogger(StoreItem.class);
...
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
{% endhighlight %}

#Run

{% highlight bash %}
      [jar] Building jar: /home/vineet/workspace/javatutorials/tutorials-code/StoresWithSpringAop/build/StoresWithSpringAop.jar

run:
     [java] Hello World
     [java] Item: 
     [java]  .. ERP Code: E32334
     [java]  .. Description: Shirt XL Cotton - Red - Arrow
     [java]  .. Description: Shirt L Cotton - Red - Arrow
     [java] 14:23:32.729 [main] TRACE com.dakinegroup.StoreItem - Item: 
     [java] 14:23:32.730 [main] TRACE com.dakinegroup.StoreItem -  .. ERP Code: E32334
     [java] 14:23:32.730 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Shirt XL Cotton - Red - Arrow
     [java] 14:23:32.730 [main] TRACE com.dakinegroup.StoreItem -  .. Description: Shirt L Cotton - Red - Arrow
{% endhighlight %}


[Back to home](index.html)
---
layout: docs
overview: true
title: To Tomcat 7
---

#Getting Tomcat7 ready

#Adding deployment descriptor

Deployment descriptor has two important elements

+ Context paths and their mapping to servlet
+ Context paths and their mapping to filters

Also, it has to comply to the schema definition, which in our case shall comply to 3.1 version of javax.servlet-api

Note in the definitions of above, first a servlet/filter is defined and given a name and later this is used for mapping to a particular path. 

Example from our code is given below:
{% highlight bash %}
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
  <servlet>
    <display-name>HelloWorldServlet</display-name>
    <servlet-name>HelloWorldServlet</servlet-name>
    <servlet-class>com.dakinegroup.helloworld.HelloWorldServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>HelloWorldServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>

</web-app>
{% endhighlight %}

#Adding Test Code for this servlet
HelloWorldServlet is created in another sub-package com.dakinegroup.helloworld. Also, notice here the use of annotation, which makes use of deployment descriptor redundant. You can experiment with that. For now, lets keep using deployment descriptor.

{% highlight java %}
package com.dakinegroup.helloworld;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

//@WebServlet(name="hello",urlPatterns={"/hello"})
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {
        resp.getOutputStream().write("Hello World.".getBytes());
    }
}
{% endhighlight %}

#Modifying build.gradle

We need to add "providedCompile" dependency for javax.servlet-api:3.0.1, as that is provided by tomcat container by default. runtime dependency is required to run jetty container, if required. We will be using the pre-existing tomcat7 container in this case.

{% highlight bash %}
apply plugin: 'java'
...
apply plugin: 'war'
..
..
dependencies {
    providedCompile 'javax.servlet:javax.servlet-api:3.0.1'
    runtime 'javax.servlet:jstl:1.1.2'
    ...
    ..
}

{% endhighlight %}

Now run gradle build root project directory. Later deploy is manually by copying it to ``webapps`` directory in tomcat7 and restart container.
{% highlight bash %}
$ gradle war
$ cp build/lib/StoresWithMaven.war /opt/tomcat/webapps
$ /opt/tomcat/bin/shutdown.sh
$ /opt/tomcat/bin/startup.sh
{% endhighlight %}

Now goto, http://localhost:8080/StoresWithMaven/hello to see the page rendered by HelloWorldServlet.

##Automate war deployment
###Copy war

###Restart Tomcat



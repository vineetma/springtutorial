---
layout: docs
overview: true
title: Integrating with Web
---

#Create API for stores, store items
From here we jump into a serious design, where our stores will get converted to a server providing service for creating and storing items. Following are the service requests that one can think of:

+ Create a store
+ Create an item
+ Create a category of items
+ Receive item in store
+ Issue item from store
+ Move item from one location to other in a store
+ Update item
+ Delete item
+ Get list of items in master data
+ Get list of items in stores with balances
+ Get list of items issued on a day
+ User authentication
    * login
    * change password
    * forget password
    * create user
    * assign role
    * delete user
    * disable user
+ Create an indent
+ Create purchase order

and so many more. We will create all of them over time and keep it explained here. Your contributions will be highly appreciated.

Lets take a break from ant and adopt maven. It is far easier and our work load is just going to increase.

#Create new project with Maven
Refer to this [article](http://spring.io/guides/gs/rest-service/#initial), from where this implementation is inspired/guided.

{% highlight bash %}
$ mkdir StoresWithMaven
$ cd StoresWithMaven
$ mkdir -p src/main/java/com/dakinegroup/storesApp
$ tree
|-- pom.xml
|-- src
|   `-- main
|       |-- java
|       |   `-- com
|       |       `-- dakinegroup
|       |           `-- storesApp
|       |               |-- StoreController.java
|       |               |-- StoreItem.java
|       |               |-- Store.java
|       |               `-- StoresApp.java
|       `-- resources
|           `-- log4j2.xml
`-- target
   

{% endhighlight %}


#Create pom.xml - webservice

Notice following things here:

+ addition of log4j2 dependencies. They are adopted from log4j2 site.
+ addition of commons-logging dependencies. They were requried by spring framework. Likely that this will get resolved automatically through spring framework dependency.
+ name of the artifactid is same as the name of the app file that will be generated
+ groupId maps to the scoping of our work to particular unique group

{% highlight bash %}
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.dakinegroup.storesApp</groupId>
    <artifactId>stores-app</artifactId>
    <version>0.1.0</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.2.5.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.1.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.3</version>
        </dependency>
    </dependencies>

    <properties>
           <java.version>1.8</java.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-releases</id>
            <url>https://repo.spring.io/libs-release</url>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <id>spring-releases</id>
            <url>https://repo.spring.io/libs-release</url>
        </pluginRepository>
    </pluginRepositories>
</project>

{% endhighlight %}

#Modifications to Store.java, StoreItem.java

Edit StoresApp.java as
{% highlight java %}

package com.dakinegroup.storesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoresApp {

    public static void main(String[] args) {
        SpringApplication.run(StoresApp.class, args);
    }
}
{% endhighlight %}

Copy Store.java and StoreItem.java from previous project to src/main/java/com/dakinegroup/storesApp/



##Changes to Store.java
Since here we are starting with Store as first, as this class was ignored, lets make some fixes here for 

+ null pointer exception
+ enabling log4j2 logging
+ renaming package to com.dakinegroup.**storesApp**

{% highlight java %}
package com.dakinegroup.storesApp;
...
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
...
private static final Logger logger = LogManager.getLogger(StoreItem.class);
...
public void displayInfo() {
    logger.trace("Store Id: "+this.storeId);
    if(items != null) {
    Iterator<StoreItem> it = items.iterator();
    while(it.hasNext()) {
{% endhighlight %}

##Changes to StoreItem.java
Make following changes to StoreItem.java

+ renaming package to com.dakinegroup.**storesApp**

##Copy log4j2.xml
Copy this file to src/main/resources directory. This works out to be same as src/ in our earlier sesson.

#Run

Now run maven to build project
{% highlight bash %}
$ mvn package
$ java -jar target/stores-app-0.1.0.jar
{% endhighlight %}

Source code till this point can be downloaded from [here](https://github.com/vineetma/springtutorial/tree/a6ecd6a5438518f87ab857cba37095b8051037c4/StoresWithMaven)

#Adding Front End (AngularJs)


#Run


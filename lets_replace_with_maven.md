---
layout: docs
overview: true
title: Go with Maven
---
#Introduction
Maven is build upon ant. Understanding of ant is essential and in fact using ant is good till the time you don't need Maven. Till when? Why Maven?.

Maven is a great tool for following things, very apprent for first time users:

+ able to generate quick project templates and first build is ready in no time
+ manage dependencies across different modules under development by different teams. Basically the project is quite big and divided into different modules.

#Installation
Execute following command to install:

{% highlight bash %}
$ sudo apt-get install maven
{% endhighlight %}

#Quick look - usage

Following commands geneate a project based on quickstart template provided out of the box by maven. While doing so, it also updates/retrieves all dependencies for the project.

{% highlight bash %}
$ mvn archetype:generate -DgroupId=com.dakinegroup -DartifactId=StoresWithSpringAop -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
{% endhighlight %}

Following command helps to clean, build, package, test
{% highlight bash %}
$ cd StoresWithSpringApp
$ mvn clean package
{% endhighlight %}

Following command helps to create documentation static site for project, in no time 
{% highlight bash %}
$ mvn site
{% endhighlight %}

#Understanding Dependency Management
Maven project has got one pom.xml (project object model). This provides description of dependencies in following format

{% highlight xml %}
<dependencies>
      <dependency>
      <groupId>com.dktapps.Accounts</groupId>
         <artifactId>accounts-api</artifactId>
         <version>0.0.2</version>
      </dependency>
</dependencies>
{% endhighlight %}
Here there is a package com.dktapps.Accounts which has got project name as accounts-api, version 0.0.2. This package is required by the project to which this pom.xml belongs.

Having managed the dependencies for resources, it also manages plugins as well. All tasks performed, like ant, in case of maven are also dependent on the plugins installed. Plugins are specified as:

{% highlight xml %}
 <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-release-plugin</artifactId>
    <version>2.0-beta-9</version>
    <configuration>
       <useReleaseProfile>false</useReleaseProfile>
       <goals>deploy</goals>
       <scmCommentPrefix>[bus-core-api-release-checkin]-<
       /scmCommentPrefix>
    </configuration>
</plugin>
{% endhighlight %}

[List of all plugins (tasks)](http://maven.apache.org/plugins/index.html)

#Pooling all dependencies across projects

If we have multiple projects which have similar dependencies. We can create common pom.xml, where we keep all these dependencies. This common pom is than made parent of all the projects who look for these dependencies. Maven automatically discovers them while traversing dependency chain and retrieve.

#Archetypes
It would be interesting to refer to this [page](http://maven.apache.org/archetype/index.html) for ready to use project templates.

One such is shown as example above - quickstart

#Generating eclipse project
Maven provides generation of eclipse project file right-a-way:
{% highlight xml %}
$ mvn eclipse:eclipse
{% endhighlight %}

For complex projects, refer to [maven official page](http://maven.apache.org/plugins/maven-eclipse-plugin/usage.html)

#Application to Stores project

We shall not use this in our project. We want to stick to basics so that we understand what is going on the ground. For now Ant is good enough, we will stick to that. Let the need arise. It was important to know of maven. We will come back to it's usage in project soon.

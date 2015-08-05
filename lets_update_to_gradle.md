---
layout: docs
overview: true
title: Move to Gradle
---
Gradle is the defacto build tool, having support for major IDEs. Advantage here is that it is capable to work across multiple projects. Like maven, it is able to deal with dependencies and create projects using default templates.

Gradle has a concept similar to ant i.e tasks (which in ant were called as target). Unlike maven, Gradle has integratoin with ant build files, even can generate them. Gradle passes control to other gradle files which would be for the sub-projects (referred as projects in Gradle).

Like maven, this is also based on conventions rather than configuration. So things stay simpler and standardized.

more coming in to help write and read gradle for common syntax...

A very useful, all time reference can be used from [here](https://docs.gradle.org/current/userguide/userguide.html)

#Install
On ubuntu, following packages need to be installed:

{% highlight bash %}
$ sudo apt-get install libtomcat7-java
$ sudo apt-get install --fix-missing libjetty-extra-java 
$ sudo apt-get install libgradle-plugins-java 
$ sudo apt-get install gradle

{% endhighlight %}
#Minimal Design

As we know now, gradle is about collection of tasks. A task can be to do anything, including just printing a message. For example:

{% highlight bash %}
task printhello {
    doLast {
      println "Hello world!"
}
}
{% endhighlight %}

Or

{% highlight bash %}
task printhello  << {
      println "Hello world!"
}
{% endhighlight %}

Language that is used inside the task is called ["groovy"](http://www.groovy-lang.org/documentation.html#gettingstarted)

On similar lines, how to capture dependencies

{% highlight bash %}
task saycity << {
    println "I am from Gurgaon, India"
}
task printhello(dependsOn: saycity) << {
    println "My name is Vineet"
}
{% endhighlight %}

In above code, notice the inverted single quote and that the task it depends on, is coming later. This keeps the pain of ordering tasks away, good for big projects.

Now, using the power of groovy, one can dynamically create tasks. This reminds me of old "make", where we used to use bash scripts and have dynamic builds.
{% highlight bash %}
4.times {
    counter ->  
      task "task$counter" << {
         println "I am task number $counter"
      }
    }
{% endhighlight %}

It keeps getting interesting. Now, we can change the tasks properties as well. For example: we keep adding dependencies to already defined task. They call it using "api", like a method on object "task".

{% highlight bash %}
4.times {
    counter ->  
      task "task$counter" << {
         println "I am task number $counter"
      }
    }
task0.dependsOn task2, task3

{% endhighlight %}

On similar lines, other apis "doLast", "doFirst" may be accessed for the task and their behaviour can be modified.

"task" is like a processing node / object. It has got it's predefined properties and can also be extended to add more properties. Keyword used for this purpose is - "ext". Checkout:

{% highlight bash %}
task printhello  {
    ext.name2 = "Vineet"
}
task printResume << {
    println printhello.name2
}
{% endhighlight %}

Note here that we have to remove "<<" from the task object. Why?. "<<" was a shortcut for doLast method within the task. If we have to do something else, we than need to either extend the task outside or do without "<<" syntax.

By now, little bit of groovy would have become familiar. Let me list here, so that one can connect:

+ groovy works on a concept of command name followed by blocks, which later can be referred. name here is optional.
+ there would be some standard commands, and there are some built by tool implementation like "task" in case of gradle. And likely, that user within gradle can also create new commands.
+ there is iterator "each", which can be attached to lists. First line there will be 

A small example where, we use groovy to marshal directories and execute ant files within them:

{% highlight bash %}
task loadfile << {
    def files = file('tests').listFiles().sort()
    files.each {
        File file -> 
        if(file.isFile()) {
            ant.loadfile(srcFile: file, property: file.name)
            println "*** $file.name ****"
            println "***${ant.properties[file.name]}"
        }
    }
}
{% endhighlight %}

#Tasks
Like ant, you can define default tasks
{% highlight bash %}
defaultTasks 'clean', 'run'
{% endhighlight %}

There are more things, just because of power of programming language embedded in it. Checkout the official page, from where the above examples are adopted.

Lets create a sample project, quickly.

{% highlight bash %}
apply plugin: 'java'
{% endhighlight %}
Output

{% highlight bash %}
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar
:assemble
:compileTestJava UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test UP-TO-DATE
:check UP-TO-DATE
:build

BUILD SUCCESSFUL

{% endhighlight %}

##Adding repositories

``repositories`` task is special keyword. This is to be initialized by user.

{% highlight bash %}
repositories {
    mavenCentral()
}
{% endhighlight %}

##Adding dependencies

``dependencies`` task is another keyword to capture dependencies of project on external resources.
{% highlight bash %}
dependencies {
    compile group: 'commons-collections', name:'commons-collections', version: '3.2'
    testCompile group: 'junit', name:'junit', version:'4.+'
}
{% endhighlight %}

Here compile, testCompile are pseudo targets that map to implicit targets used in java plugin defined tasks. It says, whenever those tasks are to be executed, following repositories need to be searched and retrieved for specific version.

##Customizing project

As we saw applying a plugin like 'java', there are several default tasks are added implicitly, which are visible only when we run build. Now, we may like to change some properties of these tasks. As shared earlier, gradle allows to customize tasks after they are defined. Lets see an example of this application here:

{% highlight bash %}
...
jar {
    manifest {
        attributes 'Main-class': 'StoresApp',
                   'Implementation-Version': '1.0'
    }
}
...
{% endhighlight %}

Here only the manifest file property is being modified.

Similarly there are properties at project level. Single gradle file at the top level represents project and any property defined at the top level are properties of project. For example: version of the project, sourceCompatibility

{% highlight bash %}
sourceCompatibility = 1.5
version = '1.0'
{% endhighlight %}

##Multiple projects

To build multiple projects, we need to provide with settings file (settings.gradle) with projects listed.

{% highlight bash %}
    include "shared", "api", "services:webservice", "services:shared"
{% endhighlight %}

Notice here ":" used instead of "/" to separate directories.

Further in case of multiple projects, there would be need to have common configuration as it is coded same in all the gradle files. Such common information is maintained at the project root directory underwhich sub-projects are maintained. Root directory acts like a container. Gradle in root directory shall provide an injection of this configuration to each project under this container. Lets see an example of such configuration file at the project root directory.

{% highlight bash %}
subprojects {
    apply plugin: 'java'
    repositories {
       mavenCentral()
    }
    dependencies {
        testCompile 'junit:junit:4.12'
    }
    version = '1.0'
    jar {
        manifest.attributes provider: 'gradle'
    }
}
{% endhighlight %}

Gradle file in individual sub-projects shall have further modification/addition to the properties received from root, as the need may be.

#Adding eclipse support
It is as simple as

{% highlight bash %}
apply plugin: 'eclipse'
{% endhighlight %}

#Relevant Templates
**TODO**: Include reference to the URL where such templates are mentioned. 
**TODO**: List commonly used, specifically the one that we would like to use for the storesapp application. 


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
task printhello(dependsOn: 'saycity') << {
    println "My name is Vineet"
}
task saycity << {
    println "I am from Gurgaon, India"
}

{% endhighlight %}

In above code, notice the inverted single quote and that the task it depends on, is coming later (saycity). This keeps the pain of ordering tasks away, good for big projects.

Now as we run this script, we see following output:
{% highlight bash %}

$ gradle printhello
:saycity
I am from Gurgaon, India
:printhello
My name is Vineet

BUILD SUCCESSFUL

Total time: 2.534 secs
{% endhighlight %}


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

Learning groovy could be handy to derive maximum in gradle build scripts.

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

##Closures

Gradle uses groovy and hence one of the important concepts, closure is used extensively. In above example for ``loadfile``, ``files.each`` command has an arugment of closure. This closure takes ``file`` as an argument and it is followed by the code block. Argument and the code block is to be surrounded by curly braces. 

It seems to be same in functionality of callback, function pointers as used in javascript / c/c++. This means, ``files.each`` shall iterate over each file and that shall be sent as an arugment to the closure. This closure than will execute for every such find. Closure here may also be called as anonymous method/function.

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

##Task types
There are several task types [look here for Copy](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Copy.html). This is provided as one of the arguments while defining the task e.g. ``task copyDocs(type: Copy) {}``

Other types of tasks are: ``delete``, ``Jar``, ``Javadoc``, ``JavaExec``, ``Tar``, ``War``, ``Zip``. All of them can be explored more from [Gradle Doc Site](https://docs.gradle.org/current/dsl/)

##Task properties
Each task has a closure, which will define specifications for the task. These specifications consist of 

+ properties
+ methods

these properties and methods are specific to the type of the task and any generic properties / methods available. for example:

{% highlight bash %}

task initConfig(type: Copy) {
    from('src/main/config') {
        include '**/*.properties'
        include '**/*.xml'
        filter(ReplaceTokens, tokens: [version: '2.3.1'])
    }

into 'build/target/config'
exclude '**/*.bak'

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

Customize the jar properties, i.e. manifest content.

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

#Listing tasks available in build

{% highlight bash %}
$ gradle tasks
:tasks

------------------------------------------------------------
All tasks runnable from root project
------------------------------------------------------------

Application tasks
-----------------
bootRun - Run the project with support for auto-detecting main class and reloading static resources
installApp - Installs the project as a JVM application along with libs and OS specific scripts.
run - Runs this project as a JVM application

Build tasks
-----------
assemble - Assembles the outputs of this project.
bootRepackage - Repackage existing JAR and WAR archives so that they can be executed from the command line using 'java -jar'
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles classes 'main'.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles classes 'test'.

Build Setup tasks
-----------------
init - Initializes a new Gradle build. [incubating]
...
...
{% endhighlight %}

#Run the code
It may be noticed that in the sample gradle file we have not specified any main entry point. Gradle finds it out and prepares manifest file on it's own. As stated earlier, it is possible to update manifest file by changing it's properties in gradle. However, out of the box build solution from gradle works for most purposes.

{% highlight bash %}
$ gradle bootRun
{% endhighlight %}

#Create an executable jar
{% highlight bash %}
$ gradle bootRepacakge
{% endhighlight %}

#Moving StoresApp to gradle

Picked up one of the generic script here from samples around spring. Two important things to note here:

+ name of the jar file and it's version. We kept it same as we had specified in pom.xml for maven
+ dependencies to include log4j2 library.

Now as we build, gradle, automatically fetches the dependencies, make executable jar and even run that.

**build.gradle**
{% highlight bash %}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:1.2.5.RELEASE")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'spring-boot'

jar {
    baseName = 'stores-app'
    version =  '0.1.0'
}

repositories {
    mavenCentral()
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencies {
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile group: 'org.apache.logging.log4j', name:'log4j-core', version:'2.3'
    testCompile("junit:junit")
}

task wrapper(type: Wrapper) {
    gradleVersion = '2.3'
}
{% endhighlight %}

To run:

{% highlight bash %}
$ gradle bootRun
{% endhighlight %}

Source for this change is available [here](https://github.com/vineetma/springtutorial/tree/eced7a66820f9d96629a75a8f182dbb1515fd891/StoresWithMaven).
#Relevant Templates
**TODO**: Include reference to the URL where such templates are mentioned. 
**TODO**: List commonly used, specifically the one that we would like to use for the storesapp application. 

  
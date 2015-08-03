---
layout: docs
overview: true
title: Create JAR
---
Updated code for this part is available [here](https://github.com/vineetma/springtutorial/tree/5f816b49a7c55b003a605d99c2998e26d63cb3e0/StoresWithSpringAop)
#Manually create jar

{% highlight bash %}
$ pwd
$ ant
$ cd build/classes
$ jar cvf StoresWithSpringAop.jar *
added manifest
adding: com/(in = 0) (out= 0)(stored 0%)
adding: com/dakinegroup/(in = 0) (out= 0)(stored 0%)
adding: com/dakinegroup/Store.class(in = 2172) (out= 988)(deflated 54%)
adding: com/dakinegroup/Test.class(in = 600) (out= 404)(deflated 32%)
adding: com/dakinegroup/StoreItem.class(in = 1408) (out= 649)(deflated 53%)
$ jar tvf StoresWithSpringAop.jar 
     0 Mon Aug 03 10:44:26 IST 2015 META-INF/
    68 Mon Aug 03 10:44:26 IST 2015 META-INF/MANIFEST.MF
     0 Mon Aug 03 09:40:42 IST 2015 com/
     0 Mon Aug 03 10:44:14 IST 2015 com/dakinegroup/
  2172 Mon Aug 03 10:44:14 IST 2015 com/dakinegroup/Store.class
   600 Mon Aug 03 10:44:14 IST 2015 com/dakinegroup/Test.class
  1408 Mon Aug 03 10:44:14 IST 2015 com/dakinegroup/StoreItem.class

# lets try to run this
$ java -jar StoresWithSpringAop.jar 
no main manifest attribute, in StoresWithSpringAop.jar

{% endhighlight %}

##Add manifest file

Notice ``added manifest`` displayed in output of jar command. If we extract that file and view, we find
{% highlight bash %}
Manifest-Version: 1.0
Created-By: 1.7.0_45 (Oracle Corporation)
{% endhighlight %}

Actually the purpose of manifest file is to provide inputs to its runnables. Most importantly, it identifies the entry point (main class).

Notice the error printed towards the end of previous command session above. It says ``no main manifest attribute...``


To do it properly so that it is useful, lets create a manifest file ourselves. Lets create a file ``manifest.mf`` in ``src`` directory.

{% highlight bash %}
Main-Class: com.dakinegroup.Test
{% endhighlight %}

Lets rephrase our jar command to include manifest file inputs

{% highlight bash %}
$  jar cvfm StoresWithSpringAop.jar ../../src/manifest.mf *
added manifest
adding: com/(in = 0) (out= 0)(stored 0%)
adding: com/dakinegroup/(in = 0) (out= 0)(stored 0%)
adding: com/dakinegroup/Store.class(in = 2172) (out= 988)(deflated 54%)
adding: com/dakinegroup/Test.class(in = 600) (out= 404)(deflated 32%)
adding: com/dakinegroup/StoreItem.class(in = 1408) (out= 649)(deflated 53%)
adding: manifest.mf(in = 33) (out= 35)(deflated -6%)
ignoring entry META-INF/
ignoring entry META-INF/MANIFEST.MF

{% endhighlight %}
##Run jar file
Now lets run the newly created jar file in ``build/classes`` directory
{% highlight bash %}
java -jar StoresWithSpringAop.jar 
Hello World
Item: 
 .. ERP Code: E32334
 .. Description: Shirt XL Cotton - Red - Arrow
 .. Description: Shirt L Cotton - Red - Arrow
{% endhighlight %}

**Notes**

+ why we created jar? It organizes scattered classes into one file. Command line is easier to use. It provides an executing environment with preset inputs.

##Add jar to ant

Add following target to ``build.xml`` file.
{% highlight xml %}
<target name="jar" depends="compile">
 <jar destfile="${jar.file}" 
      basedir="${classes.dir}"
      manifest="${src.dir}/manifest.mf">
</jar>
{% endhighlight %}

**Notes**

+ it is kept very simple. Following may be added by using the [help](https://ant.apache.org/manual/Tasks/jar.html): excludefiles, includefiles, compress

Modify ``run`` target to add dependency on jar. We don't want now to continue with individually running Test.class.

{% highlight xml %}
<target name="run" depends="jar">

{% endhighlight %}

Lets run it once again, to sure, we have not spoiled anything:

{% highlight bash %}
ant run
Buildfile: /home/vineet/workspace/javatutorials/tutorials-code/StoresWithSpringAop/build.xml

compile:

jar:

run:
     [java] Hello World
     [java] Item: 
     [java]  .. ERP Code: E32334
     [java]  .. Description: Shirt XL Cotton - Red - Arrow
     [java]  .. Description: Shirt L Cotton - Red - Arrow

BUILD SUCCESSFUL
Total time: 0 seconds
{% endhighlight %}
[Back to home](index.html)
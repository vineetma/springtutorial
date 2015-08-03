---
layout: docs
overview: true
title: Add log4j support
---
We are left with two things to finish our basic layout of stores application (i mean no functionality still). 

+ adding log4j -- to provide logging functionality to our code
+ adding junit -- to get us started on test driven development as well as automated unit testing jigs.

Source code for log4j implementation in our code is available [here]().

#Where to get these libraries?

You can download junit relevant libraries from [here](https://github.com/junit-team/junit/wiki/Download-and-Install)

#Testing for StoreItem - StoreItemTest.java

{% highlight xml %}
package com.dakinegroup;
import org.junit.Before;
import org.junit.Test;
public class StoreItemTest extends junit.framework.TestCase {
   private StoreItem si;
   @Before
   public void setUp() {
     si = new StoreItem();
   }

   @Test
   public void testSetDescription1() {
     System.out.println("Testing setDescription..");
     si.setDescription1("Item 1");
     assertEquals("Item 1",si.getDescription1());
   }
}

{% endhighlight %}

#Modifications to ant file

{% highlight xml %}
<property name="lib.dir" value="${base.dir}/lib" />
.....
<target name="compile">
   <javac includeantruntime="false" srcdir="${src.dir}" 
          destdir="${classes.dir}"
          classpathref="classpath" />
....
<path id="classpath">
  <fileset dir="${lib.dir}" includes="**/*.jar" />
</path>
<path id="application" location="${jar.file}" />
....
<target name="junit" depends="jar">
       <junit printsummary="yes">
<formatter type="plain" usefile="false" />
            <classpath>
                <path refid="classpath"/>
                <path refid="application"/>
            </classpath>

            <batchtest fork="yes">
                <fileset dir="${src.dir}">
                  <include name="**/*Test.java"/>
                  <exclude name="**/Test.java" />
                </fileset>
            </batchtest>
        </junit>
  </target>

{% endhighlight %}

#Run


[Automate Testing](lets_add_junit.html)

[Back to home](index.html)
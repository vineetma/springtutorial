---
layout: docs
overview: true
title: Automate testing
---

Source code for this step is available [here](https://github.com/vineetma/springtutorial/tree/f8544cbe94b3044196c33eb4b70a8cc54b839bf0)

#Where to get these libraries?

You can download junit relevant libraries from [here](https://github.com/junit-team/junit/wiki/Download-and-Install)

#Testing for StoreItem - StoreItemTest.java
It is a convention to name the test cases using following:

+ start with small "test" string
+ must have name of the method being tested
+ must further explain in the method name what is getting tested

Annotations before the method indicate when this test case should be run, should it be run before the class gets running, or after or is a single run test case. In our case, we have one @Before, to instantiate the class under test.

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

We want to add junit, for that, we need to add:

+ Junit Test case file
+ Add class path to this junit to pick up junit jar files and application jar file
+ local options for junit to display results (these can be saved to file also. [Checkout](https://ant.apache.org/manual/Tasks/junit)

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

Now run ant script.
{% highlight bash %}
junit:
    [junit] Running com.dakinegroup.StoreItemTest
    [junit] Testsuite: com.dakinegroup.StoreItemTest
    [junit] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 sec
    [junit] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 sec
    [junit] ------------- Standard Output ---------------
    [junit] Testing setDescription..
    [junit] ------------- ---------------- ---------------
    [junit] 
    [junit] Testcase: testSetDescription1 took 0.001 sec


{% endhighlight %}
[Adding spring framework](lets_add_spring.html)

[Back to home](index.html)
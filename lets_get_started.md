---
layout: docs
overview: true
title: Getting started
---
##Getting Ready

We shall start writing this initial piece of code using plain user friendly code editor like [sublime](http://www.sublimetext.com/). Create following directory structure:

{% highlight Java %}
StoresWithSpringAop
${HOME} <------ whatever is your home directory followed by work directory
  workspace  <----- can be anything
    StoreWithSpringAop  <-------- this is the first exercise
      src
        com
          dakinegroup
            StoreItem.java <---------  file
            Store.java <---------  file
            Test.java <---------  file
{% endhighlight %}

``StoresWithSpringAop`` is the name of our first exercise in this series.

You can download the source from [here](https://github.com/vineetma/springtutorial/tree/d1a2bc1f405665a98878386f4daaa6938902480a/StoresWithSpringAop). Try not to use it else learning will be incomplete. Your learning is weak, till you struggle yourself. This tutorial is just a small help to provide direction, tips.

For this project, lets assume it to be /home/storedev as ${HOME}. Therefore, path for src would be: ``/home/storedev/workspace/StoresWithSpringAop/src``

**Note:** Always ensure to replace home, workspace directories with what you have on your system.

##Lets code

###StoreItem.java

Create java object for store item (StoreItem.java). It has following attributes:

+ erpcode
+ description1
+ description2
+ date of creation

{% highlight java %}
package com.dakinegroup;

import java.util.Date;

public class StoreItem {
 private String description1;
 private String description2;
 private String erpcode;
 private Date dtCreated;
 
 // getters and setters
 public String getDescription1() {
     return description1;
 }
 public void setDescription1(String description) {
     this.description1 = description;
 }
 public String getDescription2() {
    return description2;
}
public void setDescription2(String description2) {
    this.description2 = description2;
}
public String getErpcode() {
    return erpcode;
}
public void setErpcode(String erpcode) {
    this.erpcode = erpcode;
}
public Date getDtCreated() {
    return dtCreated;
}
public void setDtCreated(Date dtCreated) {
    this.dtCreated = dtCreated;
}
// Constructors
public StoreItem(String erpcode, String description) {
    this.erpcode = erpcode;
    this.description1 = description;
    this.description2="";
}
public StoreItem(String erpcode) {
    this.erpcode = erpcode;
    this.description1="Default";
    this.description2="Default";
}
public StoreItem() {
    this.erpcode = "Invalid";
    this.description1="Invalid";
    this.description2="Invalid";
}

// Service methods
void displayInfo() {
     System.out.println("Item: " );
     System.out.println(" .. ERP Code: " + getErpcode());
     System.out.println(" .. Description: " + getDescription1());
     System.out.println(" .. Description: " + getDescription2());    
}

}
{% endhighlight %}


###Store.java

Create store to contain store items (Store.java). It has following attributes:

+ items
+ manager
+ location
+ id for store

Following service methods would be required:

+ addItem
+ removeItem

There shall be variations, additions, we will look at later.

{% highlight Java %}
package com.dakinegroup;

import java.util.Iterator;
import java.util.List;

public class Store {
    private List<StoreItem> items;
    private String storeId;
    private String manager;
    private String location;
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public List<StoreItem> getItems() {
        return items;
    }
    public void setItems(List<StoreItem> items) {
        this.items = items;
    }
    
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Store() {
        
    }
    public Store(String id) {
        this.storeId = id;
    }
    public Store(String id, List<StoreItem> items) {
        this.storeId = id;
        this.items = items;
    }
    public void displayInfo() {
        Iterator<StoreItem> it = items.iterator();
        while(it.hasNext()) {
            it.next().displayInfo();
        }
    }
    public void addItem(StoreItem item) {
        System.out.println("Store: addItem: " + item.getErpcode());
        items.add(item);
        //TODO: Check if this item already exists, if yes, than update other parameters
    }
    public void removeItem(String erp) {
        boolean found = false;
        System.out.println("Store: removeItem: "+ erp);
        Iterator<StoreItem> it = items.iterator();
        while(it.hasNext()) {
            StoreItem i = it.next();
            if(i.getErpcode() == erp) {
                it.remove();
                found = true;
                System.out.println("Found and removed: ERP: " + erp);
                break;
            }
        }
        if(!found) {
            System.out.println("Did not find: ERP: " + erp + ". Therefore could not remove.");
        }
    }
}
{% endhighlight %}

###Main  Test Code

Write test code in Test.Java (better to calls it main, test code is typically referred as JUnit Code) in the src directory

{% highlight Java%}
package com.dakinegroup;

public class Test {
    public static void main(String[] args) {
        system.out.println("Hello World");
        StoreItem si = new StoreItem();
        si.setErpcode("E32334");
        si.setDescription1("Shirt XL Cotton - Red - Arrow");
        si.setDescription2("Shirt L Cotton - Red - Arrow");
        si.displayInfo();
    }
}
{% endhighlight %}

##Build Process

### Individual Compile - javac
Run the following commands from the project home directory.

{% highlight bash %}
$ pwd
/home/storedev/workspace/StoresWithSpringAop
$ javac -cp build/classes -d build/classes src/com/dakinegroup/StoreItem.java
$ javac -cp build/classes -d build/classes src/com/dakinegroup/Store.java
$ javac -cp build/classes -d build/classes src/com/dakinegroup/Test.java 

{% endhighlight %}

####Notes

+ -cp - for searching the classes during build. It is short form of classpath
+ -d  - for destination directory where the compiled classes will be kept

Now your directory structure would look like this:

{% highlight bash %}
|-- build
|   `-- classes
|       `-- com
|           `-- dakinegroup
|               |-- Store.class
|               |-- StoreItem.class
|               `-- Test.class
`-- src
    `-- com
        `-- dakinegroup
            |-- StoreItem.class
            |-- StoreItem.java
            |-- Store.java
            `-- Test.java
{% endhighlight %}

### Run using command Line
{% highlight bash %}
$ java -cp build/classes com.dakinegroup.Test
Hello World
Item: 
 .. ERP Code: E32334
 .. Description: Shirt XL Cotton - Red - Arrow
 .. Description: Shirt L Cotton - Red - Arrow
{% endhighlight %}

#### Notes
+ -cp same as in compiler. meant to provide search path for classes
+ instead of directory path, we have used class notation
+ com is not the directory under current directory, however, it is able to find com.dakinegroup.Test from classpath to run

## Build using Ant

### Create build file - build.xml

Create ``build.xml`` in project root directory (``/home/storedev/workspace/StoresWithSpringAop``)

{% highlight xml %}
<project name="StoresWithSpringAop" default="run">

<property name="base.dir" value="." />
<property name="src.dir" value="${base.dir}/src" />
<property name="build.dir" value="${base.dir}/build" />
<property name="classes.dir" value="${build.dir}/classes" />

<target name="compile">
  <javac includeantruntime="false" srcdir="${src.dir}" 
          destdir="${classes.dir}"
          classpath="${classes.dir}" />
</target>
<target name="clean">
  <delete>
    <fileset dir="${classes.dir}" includes="**/*.class" />
  </delete>
</target>
<target name="run" depends="compile">
  <java classname="com.dakinegroup.Test" classpath="${classes.dir}" />
</target>
</project>
{% endhighlight %}

####Notes

+ property task - to provide re-usable variables in rest of the file
+ target - to provide which task to run from the command line of ant. Also they are anchor points for corss-references amongst targets. Note the attribute - depends.
+ depends - attribute to specify other target which should be run before current target is run
+ project - project provides entry point to ant-file, root node. It has attribute - default - to specify which task would run by default is nothing is given by user as arguments to ant.
+ places to learn more about tasks of ant
    * delete task - https://ant.apache.org/manual/Tasks/delete.html
    * javac task - https://ant.apache.org/manual/Tasks/javac.html
    * java task - https://ant.apache.org/manual/Tasks/java.html

### Run

{% highlight bash %}
$ pwd
/home/storedev/workspace/StoresWithSpringAop
$ ant
Buildfile: /home/storedev/workspace/StoresWithSpringAop/build.xml

compile:

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

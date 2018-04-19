# Leson 03 - The OSGi Blueprint Specification
The objective of this lesson is to use the Apache Aries Blueprint to create your service provider object and register it
with OSGi. This replaces using an `Activator` class to create and register the service.

New terms:
* Blueprint 
* Container
* Declarative programming
* Dependency injection
* Java bean
 
# Of Beans and Containers 
  What is a Java bean? There is a simple definition. But is it helpful? 
  According to Wikipedia:
  
  _In computing based on the Java Platform, JavaBeans are classes that encapsulate many objects 
  into a single object (the bean). They are serializable, have a zero-argument constructor, 
  and allow access to properties using getter and setter methods. The name "Bean" was given 
  to encompass this standard, which aims to create reusable software components for Java._
  
  That sounds like fancy name for an **object**. A bean is an **object** that follows a few rules. 
  It must have getters and setters that use a naming convention. This let's frameworks like
  Blueprint manipulate them more easily. A framework like Blueprint creates a "container" that
  manages the creation and initialization of beans. Containers  were created because
  everyone got tired of writing lots of code to create and build objects. All that code was 
  especially tedious when you had objects that were composed of objects, 
  which in turn were made of more objects....

  At some point, someone must have said to his team: "Let's declare all our objects in an 
  XML file and call them **beans**. We'll write code to read the XML and build up objects according
   to a specification."

  And someone else spoke up: "We can give each bean a name or ID when we declare it in the XML. 
  Then we'll make an uber object to own all of these beans and manage their lifecycle. 
  We'll call it a "container".

  The room was probably getting pretty excited when a couple of people shouted
  "dependency injection! inversion of control!". Everyone probably nodded and agreed to call it
  a "dependency injection framework". Where "dependency injection" meant to create an object and
  then add it to another object. For example, imagine an Address object and a Person object:
  
  ```java
  Address a = new Address("Kramgasse 10"); //The zero-arg constructor rule was quickly relaxed
  somePerson.setAddress(a);
  ```
  Class `Person` depends on class `Address`. Instantiating an Address and setting on a Person is
  injecting a dependency. 
  
  The *dependency injection framework* idea has been extended to do a thousand different
  things. In this lesson, we use it to create a bean and register it as an OSGi service. 
  
  
### Step 01 - Setup
* Create a new maven module with these coordinates:
```xml
<groupId>lesson</groupId>
<artifactId>03-blueprint-lesson</artifactId>
<version>1.0-SNAPSHOT</version>
```
* Create a new Java class `MyBean` in a package named `blueprintlesson`
* Add a public void method named `myInitMethod`. In the method, print something to `System.out`
  
### Step 02 - Create the blueprint file
 
* Create a *magic* directory under `src/main/resources/`:

      src/main/resources/OSGI-INF/blueprint
    
* Create an empty file named `blueprint.xml` The filename doesn't really matter, as long as it 
ends in `.xml` and is in the magic directory.
* Add the `xml` and `blueprint` elements to the file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0">
  <!--Your stuff goes here-->
</blueprint>
```

### Step 03 - Define your bean in the blueprint file
* Type this into your blueprint file. As you type it, try to think about what each piece might 
mean:

```xml
  <bean class="blueprintlesson.MyBean" id="mybean" init-method="myInitMethod"/>
```

* Check your understand by reading the comments in the `blurprint.xml` file inside this git 
repository.
 
 
### Step 04 - Build, install, and start the bundle
 * Build, install, and start your bundle
 
#### Oops. Did you get this error too?
 
 ```
Error executing command: Error executing command on bundles:
	Error starting bundle 59: Unable to resolve lesson.blueprint [59](R 59.6): missing requirement 
	[lesson.blueprint [59](R 59.6)] osgi.wiring.package; 
	(&(osgi.wiring.package=org.osgi.service.blueprint)(version>=1.0.0)(!(version>=2.0.0))) 
	Unresolved requirements: [[lesson.blueprint [59](R 59.6)] osgi.wiring.package; 
	(&(osgi.wiring.package=org.osgi.service.blueprint)(version>=1.0.0)(!(version>=2.0.0)))]
```
 
#### Stop and think
* What is the message saying?
* What command(s) would you use to diagnose the problem with the bundle?
* Try to diagnose the error. Narrow it down. Restate the issue in your own words.

#### This is what I did
* This is what I did. Right after I said "Oh crud, what went wrong?"
* Use the `headers` command we see this import is not satisfied:

      org.osgi.service.blueprint;version="[1.0.0,2.0.0)"
 	
Where did that come from? The Maven Bundle Plugin saw that someone used blueprint and 
 added that package an an import requirement:

      Import-Package: api;version="[1.0,2)",org.osgi.service.blueprint;version="[1.0.0,2.0.0)"
 
* Determine if any bundle *exports* that package to the OSGi runtime. 
Use the command `exports` and `grep`.
* Nope. Nobody exports the package. 

### Step 05 - Using Karaf features
The easiest solution is to use Karaf's *features*. A feature is like a Maven 
module for Karaf. A feature can contain multiple bundles and can declare dependencies 
on other features. Karaf features are deployed into repositories and can be installed from a 
repository. 

Search Karaf for a blueprint feature.

      feature:list | grep blueprint

Hopefully you'll see this among the results:

    aries-blueprint │ 4.2.0.M2 │          │ Uninstalled

Install the feature like so:

    feature:install aries-blueprint

* Now, start your bundle
* Verify the `myInitMethod`'s message was printed

### Take-aways
- Dependency injection frameworks like Blueprint are intended to save the developer from
writing tedious code for instantiating and connecting graphs of objects
- Using blueprint let's you remove dependencies on most or all code that interacts directly
with the OSGi runtime
- Debugging problems that don't show up in a debugger is a pain (see below)
- Karaf implements a system called **features**. For now, 
think of a feature as a "bundle of bundles" that lives in a special feature repository

 ### Sidebar - Debugging declarative code
 It can be frustrating to debug declarative code like Blueprint. While writing this tutorial, I 
 accidentally named the `OSGI-INF` directory as OSGI-INFO. The resulting bundle would start, but 
 the service was not registered. There were no error messages, no warning messages, no runtime
 exceptions. It just didn't work. 
 
 The very first time I tried to use Blueprint I created a directory named `OSGI-INF.blueprint" because
 that is how IntelliJ displays the nested directories. It was a long time before someone 
 could tell me why it wasn't working.
 
 ### References
The best Blueprint how-to guide is a 
   [2009 article from IBM Developer Works](https://www.ibm.com/developerworks/library/os-osgiblueprint/).

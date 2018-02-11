# Leson 07 - The OSGi Blueprint Specification
Objective use Apache Aries Blueprint to create your service provider object and register it
with OSGi. This replaces using an `Activator` class to create and register the service.

New terms:
* Blueprint 
* Container
* Declarative programming
* Dependency injection
* Java bean
 

  What is a Java bean? The definition is simple. But is it helpful? Wikipedia says:
  
  _In computing based on the Java Platform, JavaBeans are classes that encapsulate many objects 
  into a single object (the bean). They are serializable, have a zero-argument constructor, 
  and allow access to properties using getter and setter methods. The name "Bean" was given 
  to encompass this standard, which aims to create reusable software components for Java._
  
  That sounds like fancy name for an object. A bean is an object that follows a few rules. 
  It must have getters and setters that use a naming convention. This let's frameworks like
  Blueprint manipulate them more easily. A framework like Blueprint creates a "container" that
  manages the creation and initialization of beans. Containers  were created because
  everyone got tired of writing lots of code to create and build objects. All that code was 
  especially tedious when you had objects that were composed of objects, 
  which in turn were made of more objects....

  At some point, someone must have said to his team: "Let's declare all our object in an 
  XML file and call   them beans. We'll write code read the XML and build up objects according
   to a specification."

  And someone else spoke up: "We can give each bean a name or ID when we declare it in XML. 
  Then we'll make an uber object to own all of these beans and manage their lifecycle. 
  We'll call it a "container".

  The room was probably getting pretty excited when a couple of people shouted
  "dependency injection! inversion of control!". Everyone probably nodded and agreed to call it
  a "dependency injection framework". Where "dependency injection" meant to create an object and
  then add it to another object. Imagine an Address object and a Person object:
  
  ```java
  Address a = new Address("Kramgasse 10"); //The zero-arg constructor rule was quickly relaxed
  somePerson.setAddress(a);
  ```
  Class `Person` depends on class `Address`. Instantiating an Address and setting on a Person is
  injecting a dependency. 
  
  The *dependency injection framework* idea has been extended to a thousand different
  things. In this lesson, it is used to create a bean and register an OSGi service. 
  
  
### Step 01 - Setup
* Use the `trial` module from lesson 07
* Delete the Activator class and the activator instruction from the POM file
* Delete the dependency on `osgi.core` from the POM file
  
### Step 02 - Create the blueprint file
 
* Create a *magic* directory under `src/main/resources/`:

      src/main/resources/OSGI-INF/blueprint
    
* Grab the `blueprint.xml` file from this lesson's source code tree and stick it in the `blueprint` 
directory.
* The Maven Bundle Plugin includes the `blueprint.xml`when it packages the bundle. Karaf will 
 magically process the `blueprint.xml` file when the bundle loads. Or will it?
 
 
### Step 03 - Build, install, and start the bundle
 * Tidy up things. Uninstall the existing `sieve` and `trial` bundles
 * Build, install, and start the updated `trial` bundle
 
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
* This is what I did. Right after I said "Oh crud, what went wrong"
* Use the `headers` command we see this import is not satisfied:

      org.osgi.service.blueprint;version="[1.0.0,2.0.0)"
 	
Where did that come from? The Maven Bundle Plugin saw that someone used blueprint and 
 added that package an an import requirement:

      Import-Package: api;version="[1.0,2)",org.osgi.service.blueprint;version="[1.0.0,2.0.0)"
 
* Determine if any bundle *exports* that package to the OSGi runtime. 
Use the command `exports` and `grep`.
* Nope. Nobody exports the package. 

### Step 04 - Using Karaf features
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
* Verify the Factorizer service started using the `capabilities` command.
* Finally, try it out!

      (service:get api.Factorizer) getFactors 56


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

#### Last words
Welcome to the end of the lessons! Pat yourself on the back. Now meditate on this question: 

_Was it worth it_? 

Do the advantages of OSGi outweigh the additional complexity? What kinds of applications thrive
in an OSGi world? What kinds of applications don't?

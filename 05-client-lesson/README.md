# Lesson 05 - Consuming a Service

In the previous lesson you registered a service. In this lessons, you will write code to 
*consume* a service. This is intended to a be a quick lesson. 

### Step 01 - Set up
- Create a new Maven module in your IDE's current project.
```xml
<groupId>lesson</groupId>
<artifactId>05-client-lesson</artifactId>
<version>1.0-SNAPSHOT</version>
```

### Step 02 - Create a class
- Create a new class named `MyServiceConsumer` in package `consumer`
- Make it a implement of `BundleActivator`
- Give it the classs a variable:

      private Factorizer factorizer;
- Create accessors for the variable
- In the `start()` method, use the factorizer. For example:

      System.out.println(factorizer.getFactors(105));

- Leave the `stop()` method empty.

### Step 03 - Update POM file 
- Add the Maven bundle plugin
- Set up the POM to build the package as a bundle `<packaging>bundle</packaging>`
- Add a dependency on your factorization artifact
- Add a configuration to the maven bundle plugin to make `consumer.MyServiceConsuumer` 
the Bundle-Activator.

### Step 04 - Build and install the bundle
- Build the bundle and install it in Karaf.
- Start the bundle. Every time the bundle is started, the number is factored and the 
result is printed to the console.

Congratulations, you have now consumed an OSGi service!

#### Whiteboard pattern
If you read much about OSGi, you will quickly encounter the _whiteboard pattern_. The 
whiteboard pattern is a designg pattern. Articles on the web that describe the whiteboard
pattern often compare and contrast it to the _observer_ pattern. I wish I were an expert on 
the whiteboard pattern so I could write about the it with more confidence.

I think the OSGi registry is used in two ways. The first way is pretty straightforward. 
Service providers advertise themselves by registering a Java object. In these lessons you have
seen that code using either blueprint or in code using the `BundleContext`. Then consumers
interested in that service can obtain a `ServiceReference`, and from it, a Java object that
provides the service.

The second way the OSGi registry can be used is as a `whiteboard`. Whiteboard is a little 
misleading. A better English term would be "notice board" or "cork board". It is a place where 
you can pin notes, especially a "want ad" or maybe an "ISO". For example, "Looking for a 
calculus tutor." In software, "Looking for a Factorizer" would be more likely.

Your service consumer registers a service tracker that says let me know when providers that meet
my criteria show up (or disappear).  When services are registerd or deregistered, the 
service tracker is informed and can take action. It's considered the inverse of the 
observer pattern because it is the consumer and not the provider that registers itself.
# Adding Logging

Challenge lesson. Can you complete the lesson with minimal instructions? Try it. If you get 
stuck on something longer than 2 minutes on something, check the source code for the lesson 
and fix the problems.

```xml
<groupId>lesson</groupId>
<artifactId>logging</artifactId>
<version>1.0-SNAPSHOT</version>
```

### Create a new class
- Open your projects' **logging** module
- Create a class named `LoggingActivator` in a package named `lesson`. NOTE: For a little more 
challenge, substitute your own package, class, and method names 
- Change the class to implement the `BundleActivator` interface
- IntelliJ complains for two different reasons. What are they? How would you fix them?


**NOTE** IntelliJ will probably offer to import the wrong logging classes. The correct
imports are:

```$xslt
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
```

If you add the imports, IntelliJ will show red lines until the proper dependencies are added to the Maven POM.xml file. Don't worry about that right now.
If you are following along, it happens in a later step.

### Create activator methods
- Implement the required interface methods
- IntelliJ should have one less reason to complain. What is the source of any other errors from 
the IDE. 

### Create a logger
- Add this line to the class:

      private static final Logger logger = LoggerFactory.getLogger(LoggingActivator.class);

- IntelliJ complains. Why? How would you solve the problem?

### Update your Activator
- Add logging statements to your class. For example:
                
      logger.info("BUNDLE START");

### Missing dependencies
Fix up any missing dependencies.
```xml
 <dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-api</artifactId>
  <version>1.7.21</version>
</dependency>
<dependency>
  <groupId>org.osgi</groupId>
  <artifactId>org.osgi.core</artifactId>
  <version>6.0.0</version>
</dependency>
```

### Step 3 - Build and install
- Add the maven bundle plugin to your POM file.
- Don't forget to add the instruction that identifies which class to use as the activator.
- Test your bundle by starting and stopping it, then view the application log to verify the entries.
To view the log, use the commands `display` or `log:tail`

### Summary
Notice that our logger dependency is for an **api**. An API is usually nothing more than a collection
 of interfaces. It is doesn't know how to *do* anything. Consequently, it is small and light.
 **Karaf** provides an implementation of the logger. It is provided as part of base Karaf. 
 The OSGi runtime takes care of finding a bundle the **exports** `Logger` and `LoggerFactory`.
 `LoggerFactory` is used to create a concrete instance of a `Logger`.
 
### Take-Away 
 Bundles can depend on APIs (interfaces). Other bundles can provide one (or more) implementations 
of the API. The OSGi runtime makes those implementations available. That is why you will often see 
class or package names that end in `Impl` or `.impl`; they are implementing an API.

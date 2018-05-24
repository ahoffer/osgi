# Adding Logging

Challenge lesson. Can you complete the lesson with minimal instructions? Try it. If you get 
stuck on something longer than 2 minutes on something, check the source code for the lesson 
and fix the problems.

### Create a new maven module
Use these maven coordinates for you new module. NOTE: For extra an challenge, use your own 
names and version for the maven coordinates

```xml
<groupId>lesson</groupId>
<artifactId>logging</artifactId>
<version>1.0-SNAPSHOT</version>
```

### Create a new class
- NOTE: For a little more challenge, substitute your own package, class, and method names 
- Create a class named `LoggingActivator` in a package named `lesson`
- Change the class to implement `BundleActivator`
- IntelliJ complains for two different reasons. What are they? How would you fix them?


### Create activiator methods
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

# Leson 06 - Separating a Service from its Implementation
In this lesson, we separate the definition of the **Factorizer** service from its implementation.

### Step 1 - OSGi services
[Read more about what "service" means in OSGi](service-and-interface.md).

### Step 2 - Create a new project
* Create a new Maven project in your IDE. Do not resuse your existing project.
* In later steps, you will make three Maven modules and each one will create a bundle.
  * The first bundle will define the service API (`api`)
  * The second bundle will define the trial-division service provider (`trial`)
  * The third bundle will define the  sieve of Eratosthenes provider (`sieve`)

### Step 3 - Create the `api` module and interface
 * Use the IDE to create a new Maven module named `api`
 
 ```xml
<groupId>lesson</groupId>
<artifactId>api</artifactId>
<version>1.0-SNAPSHOT</version>
```
 
* Add this Java interface:
 
 ```java
 package api;
    
    import java.util.List;
    
    public interface Factorizer {
    
      List<Integer> getFactors(Integer number);
    }
 ```
 
 ### Step 4 - Fix up the POM file
 * Add the maven bundle plugin
 * Add the `packaging` directive `bundle`
 * Notice that there are no dependencies on OSGi packages.
 
 ### Step 5 - Build and install the API
 * Build the `api` module to create a bundle
 * Install the bundle in Karaf
 * Use the `headers` command to verify the bundle exports the `api` package

  ### What's was the point of that?
 What's was the point of that? The module named `api` defines a Java interface. The interface 
 performs no work, so why?. The interface will be used as an OSGi service. That's why.
 
 ### Why is the module named `api`?
 In the  most general case, we can think of a Java interface or OSGI service as an **API**
 
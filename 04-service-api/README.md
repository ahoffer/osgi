# Leson 04 - Defining a service API
In this lesson, define a service named **Factorizer** for finding the prime factors of a number.
In subsequent lessons we will create implementations to perform the actual factorization. 

### Step 1 - OSGi services
[Read more about what "service" means in OSGi](service-and-interface.md).

### Step 2 - Create the Java interface
* Navigate to the **service-api** module in your project. 
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
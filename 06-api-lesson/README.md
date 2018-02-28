# Leson 06 - Separating a Service from its Implementation
In this lesson, we separate the definition of the **Factorizer** service from its implementation.
Then, we will create a second service provider that factors number using a different algorithm.

### Step 1 - OSGi services
[Read more about what "service" means in OSGi](service-and-interface.md).

### Step 2 - Create a new project
* Create a new Maven project in your IDE
* Eventually you will make three Maven modules and each one will create a bundle.
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
 
 ### Step 5 - Build and install the API
 * Build the `api` module to create a bundle
 * Install the bundle in Karaf
 * Use the `headers` command to verify the bundle exports the `api` package
 
 ### Step 6 - Create the `trial` module
 ```xml
<groupId>lesson</groupId>
<artifactId>trial</artifactId>
<version>1.0-SNAPSHOT</version>
```

### Step 7 - Create `trial` service provider and activator
 * Up for a challenge? Try to writer the service provider class (`TrialDivisionProvider`) 
 yourself. 
 * Up for less of a challenge? Looks the source for `TrialDivisionProvider` and
 `MyActivator` in `07-api-lesson/trial/src/main/java/trial/` and try and recreate it.
 * If all else fails, copy and paste the code from `07-api-lesson/trial/src/main/java/trial/`

### Step 8 - Fix up the POM file
 * Add the dependencies for `osgi.core` and the Apache math library
 * Add the `packaging` directive `bundle` 
 * Add the maven bundle plugin
   * Add the instruction to use your activator class
   * Embed the Apache math library
      
### Step 9 - Build, install, and test the `trial` service provider
`(service:get api.Factorizer) getFactors 42`

### Step 10 - Create the `sieve` module
* Essentially the same steps for the `trial` module, expect the word `sieve` replaces
the word `trial`. 
* NOTE: Copy and paste at least the body of the method `getFactors` from the source code:
`07-api-lesson/sieve/src/main/java/sieve/SieveProvider.java`

### Step 11 - Build, install, and test the `sieve` service provider
Test it! First, get the service reference to the `sieve` Factorizer. 
HINT: Find the `service.id` using the `capabilities` command.

    sref=($.context getServiceReferences api.Factorizer "(service.id=115)" ) 0

Then get the actual service object:

    provider=$.context getService $sref
   
Finally, invoke the service:

    $provider getFactors 42
 
### Take Away
- A service is defined by a **Java interface**. An implementer of a service interface is called a 
service provider. Often it is just referred to as an **Impl**.
- Services are published by the OSGi runtime. 
- You look-up a service provider object by the interface it implements.
- It is outside the scope of this tutorial, but services providers can be registered with 
additional properties. You can filter on these properties to get a particular service provider.

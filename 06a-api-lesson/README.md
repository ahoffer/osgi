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
 performs no work, so why?.  The interface will be used as an OSGi service. 
 
 ### Why is the module named `api`?
 In the  most general case, we can think of a Java interface or OSGI service as an **API**
 
 
 ### Step 6 - Create the `trial` module
 This module will hold the first implementation of our `Factorizer` service. Use these maven
 coordinates:
 
 ```xml
<groupId>lesson</groupId>
<artifactId>trial</artifactId>
<version>1.0-SNAPSHOT</version>
```

### Step 7 - Create `trial` service provider blueprint
 * Up for a challenge? Try to writer the service provider class (`TrialDivisionProvider`) 
 yourself. If not, take a peek at the source code for this lesson.

### Step 8 - Fix up the POM file
 * Add the dependencies the Apache math library
 * Add the `packaging` directive `bundle` 
 * Add the maven bundle plugin
   * Add the instruction to use your activator class
   * Embed the Apache math library
      
### Step 9 - Build, install, and test the `trial` service provider
- Verify the bundle provides the `api.Factorizer` service:

      services 06b-service-provider-lesson

- Test the service:

      (service:get api.Factorizer) getFactors 42

### Step 10 - Create the `sieve` module
* `Sieve` will use a different factoring algorithm than `trial`.
* Create a maven module with these coordinates:

```xml
<groupId>lesson</groupId>
<artifactId>06b-service-provider-lesson</artifactId>
<version>1.0-SNAPSHOT</version>
```
* Create a class named `SieveProvider` in a package named `sieve`. The class should implement the 
`Factorizer` interface.
* NOTE: Copy and paste at least the body of the method `getFactors` from the source code:
`06c-service-provider-lesson/sieve/src/main/java/sieve/SieveProvider.java`

### Step 11 - Build, install, and test the `sieve` service provider
- Verify the bundle provides the `api.Factorizer` service
- What `Factorizer` provider will be returned by `service:get api.Factorizer`?
- How do we get a different provider?

### Step 12 - Query using service properties and a filter string
- A registered service in OSGi includes some basic **properties**. Properties are key-value
pairs of the property name and property value. You can add arbitrary properties to a service.
- Use the `capabilities` command to get the capabilities of your `sieve` **bundle**

```text
...
service; [api.Factorizer] with properties:
   service.bundleid = 106
   service.id = 135
   service.scope = singleton
```
- The base properties of the bundle's `api.Factorizer` service are `service.bundleid`, 
`service.id` and `service.scope`. Let's use `service.id` to get more information about this 
service provider.

- Use `service:list api.Factorizer` to get  information about all providers of `Factorizer`

### Step 13 - Add service properties to the blueprint.xml files
- Add service properties to our providers to distinguish them more easily.
- Edit the blueprint file the `trial` bundle. Add a service property inside
the `<service>` elements:

```xml
 <service-properties>
      <entry key="name" value="trial"/>
</service-properties>
```

- **Do something similar** for the `sieve` bundle

### Step 14 Test!
- Rebuild and update both `trial` and `sieve` bundles
- Verify the service properties were updated
- Get the sieve provider using an **LDAP filter**. This `getServiceReferences` return a list,
so we have to append a `0` to ask for its first element:

      sref=($.context getServiceReferences api.Factorizer "(name=sieve)") 0

- Then get the actual service object:

      provider=$.context getService $sref
   
- Finally, invoke the service:

      $provider getFactors 42
 
### Take Away
- A service is defined by a **Java interface**
- An implementer of a service interface is called a service provider.
Often it is just referred to as an **Impl**
- Services are published by the OSGi runtime. 
- Find service providers by the interfaces they implement
- Filters allow you to use service properties when finding a service
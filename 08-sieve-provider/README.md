## Lesson 08 - Implementing a _second_ service provider
 

### Step 01 - Create the `sieve` module
* The Maven module `sieve` will use a different factoring algorithm than `trial`. 
* Create a maven module with these coordinates:

```xml
<groupId>lesson</groupId>
<artifactId>sieve</artifactId>
<version>1.0-SNAPSHOT</version>
```

### Step 02 - Create the service provider class

* Create a class named `SieveProvider` in a package named `sieve`. The class should implement the 
`Factorizer` interface.
* Copy and paste at least the body of the method `getFactors` from the sample solution found in 
the `SieveProvider.java` file. 

### Step 03 - Update the POM file
* Use what you already know to update the POM file.
* Build the bundle and check the bundle manifest. Does it have what you expected?
* Compare your POM file to the POM file in the sample solution code.

### Step 04 - Register service in blueprint
* Use what you know to create the blueprint file.
* Compare your blueprint file to the sample solution code.

### Step 05 - Build, install, and inspect the `sieve` service provider
Use the `capabilities` command to verify the bundle provides the `api.Factorizer` service. It
should look something like:

```text
service; [api.Factorizer] with properties:
   service.bundleid = 106
   service.id = 135
   service.scope = singleton
```

Make sure the bundle is in the *active* state. There is no point proceeding until the bundle 
is active.

### Step 06 - Test the sieve provider
- There are now two providers how do we tell OSGi which one we want
- What `Factorizer` provider will be returned by `service:get api.Factorizer`?
- Try and it find out. 
- In my case, it returned the `trial.TrialDivisionProvider@4a65b2f`, the trial provider object.
- How do we get a different provider?

### Step  07 - Add service properties
When a service is registered, the registration can include key-value pairs that give more 
information about the service. Let's add a key "algo" whose value is "sieve".

Update the blueprint file to include the new property:

```xml
<blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0">
    <service id="sieve" interface="api.Factorizer">
        <service-properties>
            <entry key="algo" value="sieve"/>
        </service-properties>
        <bean class="sieve.SieveProvider"/>
    </service>
</blueprint>
```

### Step 08 - Rebuild and update the bundle in Karaf
Use the `capabilities` command to see the new service property. It should look something like this:

```text
service; [api.Factorizer] with properties:
   algo = sieve
   service.bundleid = 130
   service.id = 138
   service.scope = bundle
```
Look for `algo = sieve`

### Step 09 - Query using service properties and a filter string
- OSGi uses a query language called LDAP filters. We will use and LDAP filter to get the 
the sieve service provider. The LDAP `"(algo=sieve)"`
- There are no handy shortcut methods for us. First, get the service reference using the 
LDAP filter. 

`sref=($.context getServiceReferences api.Factorizer "(algo=sieve)") 0`

The command `getServiceReferences` return a list,
so we have to append a `0` to ask for its first element.


Then get the actual service object:

      provider=$.context getService $sref
      
Double check you got the right object by asking Karaf to print the name of the service
provider. Execute:

    $provider
   
Finally, invoke the service:

      $provider getFactors 42
     

 
### Take Away
- A service is defined by a **Java interface**
- An implementer of a service is called a service provider.
Often it is just referred to as an **Impl**
- Services are published by the OSGi runtime. 
- Find service providers by the interfaces they implement
- LDAP filters allow you to use service properties to find a specific service
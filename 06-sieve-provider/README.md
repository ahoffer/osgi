## Lesson 08 - Implementing a _second_ service provider
 

### Step 01 - Open your projectCreate the `sieve` module
* Navigate to the **sieve-provider** module.

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
Use the command `services -p <bundle ID>` to verify the bundle provides the `api.Factorizer` service. It
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
Use the command `services -p <bundle ID>`to see the new service property. It should look something like this:

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
- There are no handy shortcut methods for us. We have to access the system bundle context using
the predined property `$.context`. Try: 

      $.context getServiceReferences api.Factorizer "(algo=sieve)"

The command returns an array of service references. It should have one element, the reference
to the sieve provider. Let's check how many elements in the array -- it should be 1.

     ($.context getServiceReferences api.Factorizer "(algo=sieve)") length

Get the first element, index 0, of the array (the service reference)
and store it in a "shell" variable:

    sref=($.context getServiceReferences api.Factorizer "(algo=sieve)") 0
    

Now get the actual provider object and store it in another shell variable:

      provider=$.context getService $sref
      
Double check you got the right object by asking Karaf to print the name of the service
provider. Execute:

    $provider
   
Finally, invoke the service:

      $provider getFactors 42
 
### Take Away
- A service is defined by a **Java interface**
- An implementer of a service is called a service provider or an implementation.
- Services are published by the OSGi runtime. 
- Find service providers by the interfaces they implement.
- LDAP filters allow you to use service properties to find a specific service provider.
# Lesson 06 - Consuming a Service

In the previous lession you consumed a service 

### Step 01 - Set up
- Create a new Maven module in your IDE's current project.
  - Use the group ID `lesson`.
  - Use the artifact ID `client-lesson`. 
  - Name it `client-lesson`

### Step 02 - Create a class
- Create a new class named `MyServiceConsumer` in package `org.bar`
- Make it a subclass of `BundleActivator`. 

### Step 03 - Update POM file 
- Set up the POM to build the package as a bundle `<packaging>bundle</packaging>`
- Add a dependency to your factorization artifact.
- Add the Maven bundle plugin
- Add a configuration to the maven bundle plugin to make `MyServiceConsuumer` the Bundle-Activator.

### Step 04 - Add code
- Go to your subclass of `BundleActivator` and edit the `start()` method. 
- Use the bundle context to get a reference to your factorization service.
- Invoke the factorization service, passing it an integer. 
- Print the result to `System.out`.

### Step 05 - Build and install the bundle
- Build the bundle and install it in Karaf.
- Start the bundle. Every time the bundle is started, the number is factored and the result is printed to the console.

Congratulations, you have now consumed an OSGi service.

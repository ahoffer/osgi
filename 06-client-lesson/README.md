# Lesson 06 - Consuming a Service

### Step 01 - Set up
- Create a new module, package, and a subclass of `BundleActivator`. 
- 
- Set up the POM to build the package as a bundle `<packaging>bundle</packaging>`
- Add a dependency to your factorization artifact.

### Step 02 - Add code
- Go to your subclass of `BundleActivator` and edit the `start()` method. 
- Use the bundle context to get a reference to your factorization service.
- Invoke the factorization service, passing it an integer. 
- Print the result to `System.out`.

### Step 03 - Build and install the bundle
- Start the bundle. Every time the bundle is started, the number is factored and the result is printed to the console.

Congratulations, you have now consumed an OSGi service.

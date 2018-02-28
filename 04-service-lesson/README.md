# Creating an OSGi service
We will create a service that breaks a number into its prime factors. We will use an Apache 
math library to do the work. We will expose the library as an OSGi service.

### Whate are  OSGi services
[Read about what "service" means in OSGi](services.md).

### Create your module and class
Create a new maven module. The example code uses these maven coordinates: 

```xml
<groupId>lesson</groupId>
<artifactId>04-service-lesson</artifactId>
<version>1.0-SNAPSHOT</version>
``` 
 
- Create a class for the service. The sample code uses the class name `Factorizer` and the 
package name `lesson`.
- Add a single public method to the class. I called mine `getFacgtors`. Below is the method
signature. Don't worry about the body of the method. That comes later.

`public List<Integer> getFactors(Integer number) {}`


### Add a numerical library
- Add this Apache math library as a dependency in the POM file:
```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-math3</artifactId>
    <version>3.6.1</version>
</dependency>
```
### Implement the method
- Use the `Primes.primeFactors` method to do the factorization and return a list of 
prime factors

### Register the service
- Review the previous lesson about Blueprint. Create a `blueprint.xml` file for the current 
module
- Create the service object and register it as a service provider in the OSGi runtime. Add this 
to your blueprint file:

```xml
  <!--  This element will register an OSGi service under the name "factor".
    The service will advise that it implements the public methods of the "lesson.Factorizer" class.-->
  <service id="factor" auto-export="all-classes">
    <!--This is neat shortcut to create the bean (object) that implements the service.-->
    <bean class="lesson.Factorizer"/>
  </service>
  ```

### Build and install the bundle
- Build, install and start the bundle.
- Unless you did some extra work, you get an error trying to start the bundle. My error looked like this:
>Error executing command: Error executing command on bundles:
   	Error starting bundle 56: Unable to resolve lesson.04-service-lesson [56](R 56.0): missing requirement [lesson.04-service-lession [56](R 56.0)] osgi.wiring.package; (&(osgi.wiring.package=org.apache.commons.math3.primes)(version>=3.6.0)(!(version>=4.0.0))) Unresolved requirements: [[lesson.05-service-lession [56](R 56.0)] osgi.wiring.package; (&(osgi.wiring.package=org.apache.commons.math3.primes)(version>=3.6.0)(!(version>=4.0.0)))]

- Nice and cryptic! It's telling us it cannot find `org.apache.commons.math3.primes`
- **HINT:** use the `headers` and `diag` commands to get better information. Try them now.
- Why not? We included it as a maven dependency!
- OSGi imposes a new level of dependency management. It assumes there is another bundle running 
in the Karaf container that **exports** the package `org.apache.commons.math3.primes`. 
Our bundle will request to **import** the package. 
- Therefore our dependency, `org.apache.commons.math3.primes` is NOT a part of the bundle's JAR file.
- Verify this by opening the bundle's JAR file in the target directory and examining its contents.
- Because no bundle in our Karaf container exports that package, the OSGi runtime raises an error.

### Fix the dependency problem
- To solve the problem, we can **embed** the dependency, so that the class files 
of `org.apache.commons.math3.primes` are added to the bundle's JAR file.
- Include the math library in the bundle's JAR using a maven bundle plugin instruction: 
  - `<Embed-Dependency>commons-math3</Embed-Dependency>`
- Inspect the bundle's JAR file and verify the math library is now included.

### Update the bundle
- Rebuild, update the bundle. Verify it is started using `list`
- Use the command `services` to see it in the list of available services.

### Test the service from the console
Let's use the Karaf shell to interact with the service.
1. The Karaf shell lets you define variables just like any other shell. It also includes 
some special, pre-defined variables like `.console`. Use the "resolve" operator to get 
the value of a variable. Try this:

        $.context

This returns a bundle context of the root bundle, bundle 0.

2. Use the root bundle context to get a service reference for our Factorizer service:

       $.context getServiceReference "lesson.Factorizer"
       
3. Create a variable to hold the service reference (spaces matter!)

       sref=$.context getServiceReference "lesson.Factorizer"
       
4. Get a hold of the instance of Factorizer:

       $.context getService $sref
             
5. Create a variable to hold the Factorizer object

       factorizer=$.context getService $sref
       
6. Use the Factorizer to factor a number:
       
        $factorizer getFactors 42
        
### Take Away
The OSGi model is simple, in theory. Getting it to work is another matter. Bundles can be started 
and stopped while the application is running. Accounting for your imports, exports, dependencies,
 what to embed, as well as their scope or exclusions is hard. The compiler can't help you and 
 IntelliJ doesn't help you. The maven bundle plugin makes it less tedious.
 
When things don't work, you get messages about what is wrong. The more intimate you are with 
the details of how OSGi works, the more useful the messages. 

If you are used to your Java code compiling and "just working", you might be initially frustrated 
with OSGi because. OSGi turns your runtime Java into something more fluid like Ruby or JavaScript.

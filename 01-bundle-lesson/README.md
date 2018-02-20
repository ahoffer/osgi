# Lesson 01 - First Bundle

### Step 0 - Modularity in Java, Maven, and OSGi
- Read about the different views of [modularity](bundles-overview.md)
- Learn about module lifecycle in OSGi

### Step 1 - Create a maven project
- Create an *empty* project with your IDE
- Add a **Maven** module to the project. (In IntelliJ, use File->New->Module). 
Keeping things consistent will speed debugging during this session. For the modules's Maven 
coodinates, use:

```xml
  <groupId>lesson</groupId>
  <artifactId>01-bundle-lesson</artifactId>
  <version>1.0-SNAPSHOT</version>
```

### Step 2 - Add an activator class
- An Activator class is instantiated and run when the OSGi runtime environment loads a bundle.
- Add a package and class to the project. For consistency, name the class `MyActivator` and 
put it in a package named `lesson`.

### Step 3 - Add OSGi framework dependencies
- The class must implement the `BundleActivator` interface. However, Java does not know 
about this class. We have to add it as a Maven dependency.
- Add a OSGi core framework dependencies to the project by editing 
the POM file to include this dependency:

```xml
<dependency>
  <groupId>org.osgi</groupId>
  <artifactId>org.osgi.core</artifactId>
  <version>6.0.0</version>
</dependency>
```

### Step 4 - Implement BundleActivator  methods
- Edit the `MyActivator.java` file. Change the class definition to implement the interface named
`BundleActivator`
- Let your IDE import the interface. Then let your IDE implement the interface's methods for you.
Optionally, edit the .java file and add the `import`, `implement` statements yourself, then 
define the methods.
- Add `System.out.println()` statements to the `start()` and `stop()` methods. Print something
like _Bundle started!_ annd _Bundle stopped!_

### Step 6 - Build your bundle
- Invoke maven to **install** the bundle. E.g. `mvn install`
- If the process was successful, Maven creates a folder named `target/`
- Open the `target` folder and verify there is JAR file 

### Step 7 - Get Karaf and run it
- Karaf comes with an OSGi runtime environment and a handy command-line-interface for interacting
with bundles and services.
- [Download the latest (stable) binary distribution of Apache Karaf](http://karaf.apache.org/download.html) and unzip it
- Start Karaf

### Step 5 - Configure maven bundle plugin
- [Learn about the bundle manifest and the maven bundle plugin](building-bundles.md) 
- Configure the maven bundle plugin in the POM file.
- Add the `Bundle-Activator` configuration to register the activator class.

### Install and start your bundle
- Install the bundle. [See Karaf commands](karaf-commands.md)
- Start the bundle with the `start` command
- Observe:




### Step 6 - Build your bundle
- Use maven to install the project
- Open the target directory and find the JAR file
- Open the JAR file, navigate to the bundle manifest and verify the bundle activator header is set


### Step 8 - Manage your bundle

- 
- Start the bundle. Observe the printed message
- Stop the bundle. Observe the printed message

[Learn More](https://www.osgi.org/developer/architecture)

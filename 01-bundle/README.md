# Lesson 01 - First Bundle

### Modularity in Java, Maven, and OSGi
- Read about the different views of [modularity](bundles-overview.md)
- Learn about module lifecycle in OSGi

### Create a maven project
- Create an *empty* project with your IDE
- Add a **Maven** module to the project. (In IntelliJ, use File->New->Module). 
Keeping things consistent will speed debugging during this session. For the modules's Maven 
coodinates, use:

```xml
  <groupId>lesson</groupId>
  <artifactId>bundle</artifactId>
  <version>1.0-SNAPSHOT</version>
```

### Add an activator class
- An Activator class is instantiated and run when the OSGi runtime environment loads a bundle.
- Add a package and class to the project. For consistency, name the class `MyActivator` and 
put it in a package named `lesson`.

### Add OSGi framework dependencies
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

### Implement BundleActivator methods
- Edit the `MyActivator.java` file. Change the class definition to implement the interface named
`BundleActivator`
- Let your IDE import the interface. Then let your IDE implement the interface's methods for you.
Optionally, edit the .java file and add the `import`, `implement` statements yourself, then 
define the methods.
- Add `System.out.println()` statements to the `start()` and `stop()` methods. Print something
like _Bundle started!_ annd _Bundle stopped!_

### Build your bundle
- Invoke maven to **install** the bundle. E.g. `mvn install`
- If the process was successful, Maven creates a folder named `target/`
- Open the `target` folder and verify there is JAR file 

### Get Karaf and run it
- Karaf comes with an OSGi runtime environment and a handy command-line-interface for interacting
with bundles and services.
- [Download the latest (stable) binary distribution of Apache Karaf](http://karaf.apache.org/download.html) and unzip it
- Start Karaf


### Install and start your bundle
- Install the bundle. [See Karaf commands](karaf-commands.md)
- Start the bundle with the `start` command, if it isn't started
- Observe:

```Error executing command: Error installing bundles:
   	Unable to install bundle mvn:lesson/bundle/1.0-SNAPSHOT: org.osgi.framework.BundleException: OSGi R3 bundle not supported
```

- This is how you know the Maven Bundle Plugin is not configured. Think about and see if you can 
guess why Karaf provides this particular error message.

### Configure maven bundle plugin
- [Learn about the bundle manifest and the maven bundle plugin](building-bundles.md) 
- Configure the maven bundle plugin in the POM file:

```xml
 <build>
    <plugins>
      <plugin>
        <groupId>org.apache.felix</groupId>
        <artifactId>maven-bundle-plugin</artifactId>
        <version>3.5.1</version>
        <extensions>true</extensions>
      </plugin>
    </plugins>
  </build>
```

### Rebuild and reinstall bundle
What happened this time?

### Add packaging directive
 Add this line to your POM file:

    <packaging>bundle</packaging>

- Rebuild and reinstall bundle
 - Use the `list` command to verify the bundle is correctly installed

### Start the bundle
- If you have not yet started your bundle, do so now with the `start` command
- Was a message printed to the console you started the bundle? 


### Bundle Activator
- The OSGi runtime has to be told what class to invoke when the bundle is started or stopped. 
Otherwise nothing will happen.
- Add the `Bundle-Activator` configuration the POM file to register the activator class:

```xml      
<plugin>
  <groupId>org.apache.felix</groupId>
  <artifactId>maven-bundle-plugin</artifactId>
  <version>3.5.1</version>
  <extensions>true</extensions>
  <configuration>
    <!-- This section lets you add OSGi headers to the MANIFEST.MF
    We add a header to tell OSGi there is an Activator class. We use the
    use MyActivator to print messages just to test stopping and
    starting bundles. -->
    <instructions>
      <Bundle-Activator>lesson.MyActivator</Bundle-Activator>
    </instructions>
  </configuration>
</plugin>
```

- Rebuild the bundle
- Open the JAR file, navigate to the bundle manifest, and verify the `Bundle-Activator` header is set
- Use the `update` command to pull in the latest version of the bundle and start it.
- Observe the message printed to the Karaf console.

### Manage your bundle
- Start the bundle. Observe the printed message
- Stop the bundle. Observe the printed message
- Uninstall the bundle
- Use the `list` command to verify the bundle is no longer installed
- Install the bundle from your local maven repository with the command `install mvn:lesson/bundle/1.0-SNAPSHOT`
- For the rest of the tutorial, we will take a short-cut to updating bundles.
  - Execute the command `bundle:watch --start *`
  - Anytime a newer version of any bundle is installed in the local maven repository, 
  it will be loaded into Karaf and started (restarted)

### Experiment with Karaf console commands
* Try `headers`, `capabilities`, and any other commands that look
interesting.
* You can use the bundle's name or its PID (persistent ID) with these commands.


[Learn More](https://www.osgi.org/developer/architecture)

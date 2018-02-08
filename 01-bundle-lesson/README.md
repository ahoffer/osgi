# Lesson 01 - First Bundle

### Step 0 - Modularity in Java, Maven, and OSGi
- Read about the different views of [modularity](bundles-overview.md)
- Learn about module lifecycle in OSGi

### Step 1 - Create a maven project
- Create a simple maven project with a POM file

### Step 2 - Add an activator class
- Add a package and class to the project. The class must implement the  `BundleActivator` interface

### Step 3 - Add OSGi framework dependencies
- Add a OSGi core framework dependencies to the project.

### Step 4 - Implement activator methods
- Add `System.out.println()` statements to the `start()` and `stop()` methods

### Step 5 - Configure maven bundle plugin
- [Learn about the bundle manifest and the maven bundle plugin](building-bundles.md) 
- Configure the maven bundle plugin in the POM file.
- Add the `Bundle-Activator` configuration to register the activator class.

### Step 6 - Build your bundle
- Use maven to install the project
- Open the target directory and find the JAR file
- Open the JAR file, navigate to the bundle manifest and verify the bundle activator header is set

### Step 7 - Get Karaf and run it
- [Download the latest (stable) binary distribution of Apache Karaf](http://karaf.apache.org/download.html) and unzip it
- Start Karaf

### Step 8 - Manage your bundle

- Install the bundle. [See Karaf commands](karaf-commands.md)
- Start the bundle. Observe the printed message
- Stop the bundle. Observe the printed message

[Learn More](https://www.osgi.org/developer/architecture)

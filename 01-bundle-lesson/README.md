# Lesson 01 - First Bundle

### Step 1 - Create a maven project
- Create a simple maven project with a POM file

### Step 2 - Configure maven bundle plugin
- [Learn about the bundle manifest and the maven bundle plugin](building-bundles.md) 
- Configure the maven bundle plugin in the POM file. Among other things, this will add a dependency on the OSGi core interfaces.

### Step 3 - Add an activator class
- Add a package and class to the project. The class must implement the  `BundleActivator` interface.

### Step 4 - Implement activator methods
- Add `System.out.println()` statements to the `start()` and `stop()` methods


### Step 5 - Build your bundle
- Use maven to install the project
- Open the target directory and find the JAR file
- Open the JAR file, navigate to the bundle manifest and verify the bundle activator header is set

### Step 6 - Get Karaf and run it
- [Download the latest (stable) binary distribution of Apache Karaf](http://karaf.apache.org/download.html) and unzip it.
- Start Karaf.

### Step 7 - Manage your bundle

- Install the bundle. [See Karaf commands](karaf-commands.md).
- Start the bundle. Observe the printed message.
- Stop the bundle. Observe the printed message.

[Learn More](https://www.osgi.org/developer/architecture)
# OSGi Import, Export, Embed

## Import
Importing, exporting and embedding are OSGi concepts that probably need a little explanation.
The OSGi runtime acts a little like a gatekeeper or a customs officer. When you ask OSGi to load 
a bundle, the runtime inspects the bundles stated dependencies. These are the Java _packages_ 
listed as *imports* in the bundle's manifest. For example:


```
Import-Package: api;version="[1.0,2)",org.osgi.service.blueprint;version
 ="[1.0.0,2.0.0)"
```

Translated into English, this manifest header says: "I need someone in the OSGi environment to 
provide a Java package named `api`. I need it to at least version 1.0, but the version number cannot 
be greater than 2. I also need the Java package `org.osgi.service.blueprint`. The version number
needs to be between 1.0.0, but less than version 2.0.0.

In plain-old Java, packages only have names. For the Java Virtual Machine, two packages that 
both have the name `api` are the same thing. The JVM doesn't make a distinction. OSGi adds 
extra information about a package, like its version number in the MANIFEST.MF file. The maven
bundle plugin automatically generates the import directive for your bundle.

## Export
OSGi bundles also export Java packages. Exporting a Java package makes it visible to the entire
OSGi environment. The bundle informs the OSGi runtime what packages it wants to expose using a 
header in the MANIFEST.MF file. For example, here is a line from the `api` Maven
module:

```text
Export-Package: api;version="1.0.0"
```

This directive says to expose the Java package `api` and advertise its version number as 
`1.0.0`. OSGi uses a version number so that multiple Java packages with the same name can 
co-exist. This is handy when different parts of your application needs different versions of 
the same library.

## Embedded Dependencies
What if your bundle needs to import a Java package that is not available in the OSGi environment?

The answer is that you will not be able to install the bundle. The OSGi runtime will not be 
able to resolve the imports and the Karaf console will print an error about `missing requirements'
In an ideal world, every Java library would be available as a bundle. You would load the libary 
and it would advertise its Java packages via the OSGi runtime. 

The world is not a perfect place and there is another option. Embedding. The maven bundle plugin
adds a directive to copy Maven artifacts into your bundle file. Assuming the Maven artifact
contains the Java packages you need, the maven bundle plugin will not add those Java packages 
to the import directive in MANIFEST.MF. For example, the directive

```text
Embed-Dependency: commons-math3
```

copies the Maven artifact named `commons-math3` into the bundle's file. The
 `commons-math3` is a JAR file and contains the class files for the Apache math library. In the
 context of this lesson, that means your bundle does not need to import Java package 
 `org.apache.commons.math3.primes` because that package is provided by an embedded artifact.
 
 Note that `Embed-Dependency` is supported by the maven bundle plugin, but is not in the OSGi
 specification.
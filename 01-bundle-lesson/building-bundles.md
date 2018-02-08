## Maven & Maven-Bundle-Plugin
* Maven builds the JAR files
* Maven-Bundle-Plugin generates the special headers in the `MANIFEST.MF` file

## Using the maven-bundle-plugin
### Add the maven-bundle-plugin to the list of plugins
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.felix</groupId>
            <artifactId>maven-bundle-plugin</artifactId>
            <version>3.2.0</version>
            <extensions>true</extensions>
        </plugin>
    </plugins>
</build>
```

### Change or add the packaging to `bundle`

```xml
<project>
    ...
    <packaging>bundle</packaging>
    ...
</project>
```

### Configuring the maven-bundle-plugin
- The bundle maven plugin let's you set any bundle manifest header. It also provides defaults for many headers. 
- To set a header, add an entry to the plugins `configuration/instructions` element
- We need to set the header for bundle activator:
 ```xml
<configuration>
    <instructions>
      <Bundle-Activator>MyActivator</Bundle-Activator>
    </instructions>
</configuration>
```

### Default header values
The maven bundle plugin provides default values for some headers:

Header | Default Value
------ | -------------
Bundle-Symbolic-name | `${project.groupId}` + . + `${project.artifactId}`
Export-Package | All packages except package-default, *.impl, *.internal
Private-Package | All packages not specified in Export-Package. **NOTE**: if a package is specified in Export-Package and Private-Package it will be *exported*
Import-Package | All packages (*), which imports all packages referenced by the bundle content but not contained in it. **NOTE** Any exported packages are also imported by default, to ensure a consistent class space.
Include-Resource | Generated from the projects maven resources (typically `src/main/resources`)
Bundle-Version | `${pom.version}` but normalized to the OSGI format of `MAJOR.MINOR.MICRO.QUALIFIER`
Bundle-Name | `${pom.name}`
Bundle-Description | `${pom.description}`
Bundle-License | `${pom.licenses}`
Bundle-Vendor | `${pom.organization.name}`
Bundle-DocURL | `${pom.organization.url}`


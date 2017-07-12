## Karaf Shell Commands

#### How to install a bundle
1. `bundle:install mnv:{group ID}/{artifact ID}/{version}`
2. `bundle:install file:/{bundle_folder}/{filename}`
3. (Copy the JAR file to Karaf's `deploy` directory)

- Preferred method installation is with the maven address because you can use `bundle:watch` to update the bundle (see below).
- When a bundle is installed it is copied to a cache and is always loaded from the cache. 

#### Common bundle commands
* `bundle:install <mavenUrls>`
    * Installs a list of bundles into karaf
    * Can add the `--start` or `-s` flag to start the bundle when installed
* `bundle:start <ids>`
    * Starts the list of bundle ids
* `bundle:update <id> [location]`
    * Updates an installed bundle using the local maven repo
    * Can specify where to get the updated bundle from
* `bundle:headers <ids>`
    * Displays detailed OSGI information of given bundles. Such as when they import/export and whether or not they are satisfied
* `bundle:watch --start <ids>|<mavenUrls>`
    * Watches for updates to the specified bundles and automatically updates them
    * `--start` to start watching the specified bundles.
    * `--stop` to stop the specified bundles
    * `--remove` to remove the specified bundles
    * `--list` to show the currently watched bundles

**WARNING** `bundle:watch can be flaky. It can stop working without warning. However, dropped a bundle file into the `deploy` directory always replaces the existing bundle with the new bundle.

#### Updating Bundles
* `list -u <id>` to find bundle location
* `update <id>` to reload a bundle by its ID
* `bundle:watch <id>` to reload bundle when  Maven  installs the artifact in the local repository

##### See  [Karaf Documentation](http://supergsego.com/apache/karaf/documentation/4_x.html)

##### See [Karaf Scripting](https://svn.apache.org/repos/asf/karaf/site/production/manual/latest/scripting.html) 

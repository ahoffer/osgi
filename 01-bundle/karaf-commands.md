## Karaf Shell Commands

#### How to install a bundle
1. `bundle:install mvn:{group ID}/{artifact ID}/{version}`
2. `bundle:install file:/{bundle_folder}/{filename}`
3. (Copy the JAR file to Karaf's `deploy` directory)

- Preferred method installation is with the maven address because you can use `bundle:watch` to
 update the bundle (see below).
- When a bundle is installed it is copied to a cache and is always loaded from the cache. 
- **The `restart` command always restarts the cached bundle**. To load something that is not in the 
cache, the bundle has to be *updated* See the section "Updating Bundles". 


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


#### Updating Bundles
* `list -u <id>` to find bundle location
* `update <id>` to reload a bundle by its ID
* `bundle:watch --start <ids>|<mavenUrls>`
    * Watches for updates to the specified bundles and automatically updates them
    * `--start` to start watching the specified bundles.
    * `--stop` to stop the specified bundles
    * `--remove` to remove the specified bundles
    * `--list` to show the currently watched bundles
* `bundle:watch --start *` to watch and update *all* bundles

##### See  [Karaf Documentation](http://supergsego.com/apache/karaf/documentation/4_x.html)

##### See [Karaf Scripting](https://svn.apache.org/repos/asf/karaf/site/production/manual/latest/scripting.html) 

#### Felix Shell Tricks

##### Variables
* Set a variable `foo=3.14`
* Get a variable `$foo`
* Special ("dot") variables
  * $.context to access a bundle context (instance of `BundleContextImpl` for system bundle)
  * $.variables to access the list of defined variables
  * $.commands to access the list of defined command
  
##### Java code
You can sorta execute Java code. I've played with it an these seems to be the rules:
 - The format is `object method arg1 ag2` ...
 - Define a varible to hold the return value of `method`
 - For example, get all references for a particular service, first get the context, send it the `getServiceReferences` message, and pass the parameters: `$.context getServiceReferences com.github.ahoffer.imageresize.api.ImageResizer null`
 - If you want to send a method to a return object, group with parenthesis: `($.context getBundle 139) getLocation`
 
 Putting it all together. Get the first `ImageResizer` service provider, send it the message `recommendedFor` and pass it the parameter `"JPEG"`:
 
 `service_ref = $.context getServiceReference com.github.ahoffer.imageresize.api.ImageResizer`
 
 `($.context getService $service_ref) recommendedFor JPEG`
 

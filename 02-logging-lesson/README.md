# Lesson 02 - Adding Logging

### Step 1 - Update your Activator
Change your activator class to look like the one in this project.

### Step 2 - Missing dependencies
Fix up any missing dependencies.

### Step 3 - Build and install
Test your bundle by starting and stopping it, then view the application log to verify the entries.

###Learning Summary
Bundles can depend on APIs (interfaces). Other bundles can provide one (or more) implementations of the API. The OSGi runtime makes those implementations available. The bundle in this exercise was created without an actual logging library. It was bound to a logging library API and then found an implementer of that API already loaded into the OSGi runtime. That is why you will often see class or package names that end in `Impl` or `.impl`; they are implementing an API.

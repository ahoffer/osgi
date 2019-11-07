# OSGi Learning Laboratory

## Learn the 3 pillars of OSGi:
* Modularity
* Module lifecycle
* Services
  * Registering and retrieving services
  * Separating programming interfaces from implementations
  
----

## Getting Started

Installed on your computer:
 * Java 8 JDK 
 * Git
 * Maven
 
You:
 * Basic knowledge of Java, Git, and Maven.
 * These instructions assume you are using the IntelliJ IDE 

The course is organized into two repositories: 
  * This repository, named **osgi**. It provides the lessons and their solutions.
  The completed lessons are in the `src/` folders.
  * Another repository named **osgi-lessons**. It is the skeleton to which you will
  add files, code, and configuration information.

#### How to get the most out of this course
Type. Don't copy and paste from the solutions. Maybe here is some tedious stuff you might want to copy and paste, but the more you type it in, the more you will 
learn. Also, the less you can refer to the solutions, the more you will learn. 
Challenge yourself to see _how little_ you can look at the completed code. 

  
#### What you need to do now
The first thing to do is to clone the **osgi-lessons** repository to the computer 
you will use for this course.    

`git clone https://github.com/ahoffer/osgi-lessons.git`

The second thing you need to do is open your clone of osgi-lessons in IntelliJ. From 
IntelliJ, choose

`File -> New -> Project From Existing Sources...`

and choose the `osgi-lessons` directory.

Lastly, make sure everything is ready to go. Build the project. Change to the `osgi-lessons` directory and tell Maven to build:

`mvn install`

You should see `[INFO] BUILD SUCCESS` in a few seconds. If not, figure out why before
proceeding.

OK. Proceed to lesson 1, Bundles.

## Lesson 1: Bundles

* Modularity and life cycle
* Create a bundle 
  * Activator class
  * Bundle Context
  * Maven Bundle Plugin
* Install a bundle
* Start, stop, resolve a bundle

## Lesson 2: Add a Logger
* Add logging interface as dependency
* Use logging 

## Lesson 3: OSGi Blueprint
* OSGi Blueprint Specification
* Java beans
* Dependency injection framework

## Lesson 4: Create a Service
* Services instead of methods or functions
* Service as POJO

## Lesson 5: Consuming a service
* Getting hold of a service programmatically

## Lesson 6: API
* Decouple interface from implementation
* Create multiple providers that for the same API


### Addtional Resources
 - [OSGi Bundles and the maven-bundle-plugin](OSGi Bundles and the maven-bundle-plugin-v4-20180305_Author-Richard_Porter.pdf). 
 Tutorial by [Richard Porter](https://github.com/coyotesqrl). Covers api-implementation separation and helpful dos-and-dont'ts
 - [OSGi Troubleshooting](https://codice.atlassian.net/wiki/spaces/DDF/pages/29261826/OSGi+Troubleshooting) 
 by [Brendan Hofmann](https://github.com/brendan-hofmann)

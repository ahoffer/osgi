#Bundle "uses" directive

Most of what follows is taken from an online article by Christian Posta. [Click this link 
if you want to visit his article](http://blog.christianposta.com/osgi/understanding-how-osgi-bundles-get-resolved-part-i/).

What if we had this scenario. Bundle A exports a package, org.apache.foo, that contains a class, 
FooClass. FooClass has a method that returns an object of type BarClass, but BarClass is not 
defined in the bundle's class space, it's imported like this:

```java
public class FooClass {
public BarClass execute(){ ... }
}
```

```text
Bundle-Name: Bundle A
Import-Package: org.apache.bar;version="3.6.0"
Export-Package: org.apache.foo;version="1.2.0"

```

So far everything is fine as long as there is another bundle that properly exports
 org.apache.bar with the **correct version**.

```text
Bundle-Name: Bundle B
Export-Package: org.apache.bar;version="3.6.0"
```

These two bundles will resolve fine. Now, if we install two more bundles, 
Bundle C and Bundle D that look like this:

```text
Bundle-Name: Bundle C
Import-Package: org.apache.foo;version="1.2.0", org.apache.bar;version="4.0.0"
```

```text
Bundle-Name: Bundle D
Export-Package: org.apache.bar;version="4.0.0"
```

We can see that Bundle C imports a package, org.apache.foo from Bundle A. 
Bundle C can try to use FooClass from org.apache.foo, but when it gets the return value, 
a type of BarClass, what will happen? Bundle A expects to use version 3.6.0 of BarClass, but 
bundle C is using version 4.0.0. So the classes used are not consistent within bundles at runtime 
(i.e., you could experience some type of mismatch or class cast exception), but everything will 
still resolve just fine at deploy time following the rules from above. 

What we need is to tell 
anyone that imports org.apache.foo that we use classes from a specific version of org.apache.bar, 
and if you want to use org.apache.foo you must use the same version that we import. That's exactly 
what the uses directive does. Let's change bundle A to specify exactly that:

```text
Bundle-Name: Bundle A
Import-Package: org.apache.bar;version="3.6.0"
Export-Package: org.apache.foo;version="1.2.0"";uses:=org.apache.bar
```

Given the new configuration for Bundle A, the bundles would not resolve correctly from above. 
Bundle C could not resolve, because it imports org.apache.foo but the "uses" constraint on 
Bundle A specifies that C must use the same version that A does (3.6.0) for org.apache.bar, 
otherwise the bundle will not resolve when trying to deploy. 

One solution is change the version in Bundle C for org.apache.bar to accept version 3.6.0. 
Another solution is to update Bundle A to use version 4.0.0 of org.apache.bar.
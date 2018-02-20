
>Services offer an interface-based programming model. The only requirement on a service is that it implements an interface; any interface will do, even ones from the base JRE or third-party libraries. The chosen interface — or interfaces, since a Java object can implement many interfaces — forms the primary addressing mechanism for a service. For example, a service publisher declares “I have this object available which is Mailbox.” The consumers declare “I am looking for a Mailbox.” The Service Registry provides a venue for publishers and consumers to find each other. The consumer does not need to know the implementation class of the published service, just the interface through which it interacts. OSGi’s Service Registry has been called a form of Service Oriented Architecture, or SOA. Many people think of SOA as being associated with distributed computing, Web Services, SOAP and so on, but that is just one example of SOA, which is really just a pattern or style of architecture. OSGi services are limited in scope to a single JVM — they are not distributed — yet they map very cleanly to the concepts of SOA.

From the preview of the book "OSGi in Practice" by Neil Bartlett

In practice, the word "service" is used ambiguously. The most correct use of "service" is the 
definition of a contract:

*"If you invoke me with certain input, I promise to perform some work and return this output 
back to you."*

Java has a strong support for these kind of contracts. Java uses **interfaces** to define contracts.
An interface has a name and a collection of method signatures. The method signatures are like
the terms of the contract. The signatures define the name, the parameters and the return value of
a public method.

More generally, these kinds of contracts are called **Application Programming Interfaces** or 
**API**s. API is a general terms that could refer any informatics contract. For example:
* C/C++ header file
* Java interface 
* SOAP web service WSDL (an XML file that specifies the contract)
* REST web service's response to different HTTP requests

However, a contract by itself does nothing. The functionality defined by an interface must to be 
implemented somewhere. In fact, it can be implemented many times. And this is the other ambiguous
use of the word "service"-- the code that implements an API. To disambiguate the two uses
of "service", The letters **Impl** are sometimes added to the name of the end of a class name
to indicate it implements a service. Here is an example:

`public class BrandingRegistryImpl implements BrandingRegistry`


I prefer calling implementations **Providers**. My examples look like:

`public class SieveProvider implements Factorizer`

----


### Whiteboard Pattern
Want to geek-out? Read: https://www.osgi.org/wp-content/uploads/whiteboard1.pdf. Then explain 
it to me because I haven't taken the time to figure it all out. Although, I think a more idiomatic
term might be "pin board" and not "white board". Read it and tell me what you think.
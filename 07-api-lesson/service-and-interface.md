
>Services offer an interface-based programming model. The only requirement on a service is that it implements an interface; any interface will do, even ones from the base JRE or third-party libraries. The chosen interface — or interfaces, since a Java object can implement many interfaces — forms the primary addressing mechanism for a service. For example, a service publisher declares “I have this object available which is Mailbox.” The consumers declare “I am looking for a Mailbox.” The Service Registry provides a venue for publishers and consumers to find each other. The consumer does not need to know the implementation class of the published service, just the interface through which it interacts. OSGi’s Service Registry has been called a form of Service Oriented Architecture, or SOA. Many people think of SOA as being associated with distributed computing, Web Services, SOAP and so on, but that is just one example of SOA, which is really just a pattern or style of architecture. OSGi services are limited in scope to a single JVM — they are not distributed — yet they map very cleanly to the concepts of SOA.

From the preview of the book "OSGi in Practice" by Neil Bartlett

#### Take Away
- A service is defined by a **Java interface**. An implementer of a service interface is called a service provider. Often it is just referred to as an "impl".
- Services are published by the OSGi runtime. 
- You look-up a service provider object by the interface it implements.
- It is outside the scope of this tutorial, but services providers can be registered with additional properties. You can filter on these properties to get a particular service provider.
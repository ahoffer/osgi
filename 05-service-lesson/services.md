>You may think a service is something you access across the network, like retrieving stock quotes or searching Google. But the classical view of a service is something much simpler: “work done for another.” This definition can easily apply to a simple method call between two objects, because the callee is doing work for the caller. How does a service differ from a method call? A service implies a contract between the provider of the service and its consumers. Consumers typically aren’t worried about the exact implementation behind a service (or even who provides it) as long as it follows the agreed contract, suggesting that services are to some extent substitutable.

From the book "OSGi In Action"

Services in OSGi are really POJOs - plain old Java objects. OSGi provides the magic to discover service objects and deliver to the code that wants to use the object. 

Because the "service" is really just a Java object, the client code can invoke any public method on the object.
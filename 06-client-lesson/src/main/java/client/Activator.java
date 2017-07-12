package client;

import org.foo.Factorizer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        //The point of this lesson is how to get a service reference (interface) and then, given the
        // reference, find an object that provides the services.
        // As I understand the OSGi "whiteboard" patterns, the service are posted (announced?) on
        // a metaphorical "whiteboard". Getting a reference to a service
        // is like getting a handle to the interface posted on the whiteboard.
        // Once you have the reference, you can use the framework to get the objects that provide
        // the service.
        ServiceReference serviceReference = context.getServiceReference(Factorizer.class);
        Factorizer provider = (Factorizer) context.getService(serviceReference);


        System.out.println(provider.lookupEvent(BundleEvent.RESOLVED));
    }

    public void stop(BundleContext context) throws Exception {
    }
}

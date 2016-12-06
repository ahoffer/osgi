package client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceReference;
import service.EnumerationDictionary;

public class Activator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        //The point of this lesson is how to get a service reference (interface) and then, given the
        // reference, find an object that provides the services.
        // As I understand the OSGi "whiteboard" patterns, the service are posted (announced?) on
        // a metaphorical "whiteboard". Getting a reference to a service
        // is like getting a handle to the interface posted on the whiteboard.
        // Once you have the reference, you can use the framework to get the objects taht provider
        // the service.
        ServiceReference serviceReference =
                context.getServiceReference(EnumerationDictionary.class);
        EnumerationDictionary provider =
                (EnumerationDictionary) context.getService(serviceReference);


        System.out.println(provider.lookupEvent(BundleEvent.RESOLVED));
    }

    public void stop(BundleContext context) throws Exception {
    }
}

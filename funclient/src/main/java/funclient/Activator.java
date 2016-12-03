package funclient;

import funservice.EnumerationDictionaryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        ServiceReference serviceReference =
                context.getServiceReference(EnumerationDictionaryService.class);
        EnumerationDictionaryService service =
                (EnumerationDictionaryService) context.getService(serviceReference);
        System.out.println(service.lookupEvent(BundleEvent.RESOLVED));
    }

    public void stop(BundleContext context) throws Exception {
    }
}

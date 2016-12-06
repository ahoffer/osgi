package providers;

import api.EnumerationDictionaryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration serviceRegistration;

    public void start(BundleContext context) throws Exception {

        /* How to generate a runtime error:
           If EnumerationDictionaryService does not implement EnumerationDictionaryInterface, you
           can cast the instance of ...service to ...interface and it will compile fine. But it
           will throw a runtime error that the cast is invalid.
        */
        serviceRegistration = context.registerService(EnumerationDictionaryService.class,
                new EnumerationDictionaryProvider(), null);
    }

    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}

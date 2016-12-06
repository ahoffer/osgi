package service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration<?> serviceRegistration;

    public void start(BundleContext context) throws Exception {
        serviceRegistration = context.registerService(EnumerationDictionaryService.class,
                new EnumerationDictionaryService(), null);
    }

    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}

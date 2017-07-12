package providers;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import api.MyEventLookupService;

public class Activator implements BundleActivator {
    private ServiceRegistration serviceRegistration;

    public void start(BundleContext context) throws Exception {

        serviceRegistration = context.registerService(MyEventLookupService.class,
                new MyEventLookupProvider(), null);
    }

    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}

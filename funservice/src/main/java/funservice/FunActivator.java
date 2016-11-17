package funservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

// This activator class isn't necessary for the FunService. It was created before the service,
// just to test installing, starting, stopping, and uninstalling bundles.
public class FunActivator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        System.out.println("******* FUN START **********");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("******* FUN STOP **********");

    }
}

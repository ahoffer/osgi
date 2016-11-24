package funservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

// Test installing and uninstalling bundles.
// just to test installing, starting, stopping, and uninstalling bundles.
public class FunActivator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        System.out.println("******* FUN SERVICE START **********");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("******* FUN SERVICE STOP **********");
    }
}

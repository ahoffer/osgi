package tracker;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private FunBundleTracker bundleTracker;

    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Bundle Tracker");
        int trackStates = Bundle.STARTING
                | Bundle.STOPPING
                | Bundle.RESOLVED
                | Bundle.INSTALLED
                | Bundle.UNINSTALLED
                | Bundle.ACTIVE;
        bundleTracker = new FunBundleTracker(context, trackStates, null);
        bundleTracker.open();
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Bundle Tracker");
        bundleTracker.close();
        bundleTracker = null;
    }

}
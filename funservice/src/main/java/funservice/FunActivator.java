package funservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

// Test installing and uninstalling bundles.
// just to test installing, starting, stopping, and uninstalling bundles.
public class FunActivator implements BundleActivator, BundleListener {

    public void start(BundleContext context) throws Exception {
        String symbolicName = context.getBundle().getSymbolicName();
        String eventName = EventLexicon.lookup(context.getBundle());
        printMessage(symbolicName, eventName);
        context.addBundleListener(this);
    }

    public void stop(BundleContext context) throws Exception {
        String symbolicName = context.getBundle().getSymbolicName();
        String eventName = EventLexicon.lookup(context.getBundle());
        printMessage(symbolicName, eventName);
        context.removeBundleListener(this);
    }

    public void bundleChanged(BundleEvent bundleEvent) {
        String symbolicName = bundleEvent.getBundle().getSymbolicName();
        String eventName = EventLexicon.lookup(bundleEvent.getType());
        printMessage(symbolicName, eventName);
    }

    private void printMessage(String name, String state) {
        System.err.println(String.format("Bundle '%s' Event '%s'", name, state));
    }
}
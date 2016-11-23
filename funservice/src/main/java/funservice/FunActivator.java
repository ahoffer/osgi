package funservice;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunActivator implements BundleActivator, BundleListener {

    // The Logger and LoggerFactory definitions are pulled in through an slf4j library pulled
    // in a as a Maven dependency. The logger implementation is probably provided by
    // OPS4j Pax Logging bundle that is a part of the Karaf distribution.
    //
    //
    private final Logger logger = LoggerFactory.getLogger(FunActivator.class);

    public void start(BundleContext context) throws Exception {
        Bundle bundle = context.getBundle();
        printMessage("start()-" + bundle.getSymbolicName(), bundle.getState());
        context.addBundleListener(this);
    }

    public void stop(BundleContext context) throws Exception {
        Bundle bundle = context.getBundle();
        printMessage("stop()-" + bundle.getSymbolicName(), bundle.getState());
        context.removeBundleListener(this);
    }

    public void bundleChanged(BundleEvent bundleEvent) {
        Bundle bundle = bundleEvent.getBundle();
        printMessage("bundleChanged()-"+bundle.getSymbolicName(), bundleEvent.getType());
    }

    private void printMessage(String bundleName, int bundleState) {
        String eventName = EventLexicon.lookup(bundleState);
        logger.warn("Bundle {} {}", bundleName, eventName);
    }
}
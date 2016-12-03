package funlistener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator, BundleListener {

    private static final Logger logger = LoggerFactory.getLogger(Activator.class);

/*
 * OSGi bundles states:
 *
 * INSTALLED: The OSGi runtime knows the bundle is there.
 * RESOLVED: The bundle is there and all it’s prerequisites (dependencies) are available. The bundle can be started (or has been stopped).
 * STARTING: The bundle is being started. If it has a BundleActivator class, the BundleActivator.start() method is being executed. When done, the bundle will become ACTIVE. Note: Bundles that can be activated lazily (Bundle-ActivationPolicy: lazy) stay in this state until one of their class files is loaded.
 * ACTIVE: The bundle is running.
 * STOPPING: The bundle is being stopped. If it has a BundleActivator class, the BundleActivator.stop() method is being executed. When done, the bundle will become RESOLVED.
 * UNINSTALLED: The bundle has been removed from the OSGi runtime.
 *
 * source: http://eclipsesource.com/blogs/2013/01/23/how-to-track-lifecycle-changes-of-osgi-bundles/
 */

    public void start(BundleContext context) throws Exception {
        System.out.println("FunListener start()");
        logger.warn("FunListener register");
        context.addBundleListener(this);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("FunListener stop()");
        logger.warn("FunListener unregister");
        context.removeBundleListener(this);
    }

    public void bundleChanged(BundleEvent event) {
        // This will only ever log the START event. That is because when the bundle transitions
        // from RESOLVED to ACTIVE, the start() method is called and the listener is registered.
        // The next event to be triggered is the STARTED event.
        // When the bundle transitions from ACTIVE to RESOLVED, the listener is first unregistered.
        // When the STOPPED event is triggered, the listener is already unregistered.
        logger.warn("funlistener: Bundle {} Event {}", event.getBundle().getSymbolicName(), EnumerationDictionary.lookupEvent(event));
    }
}
package funtracker;

import funlistener.EnumerationDictionary;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class MyBundleTracker extends BundleTracker {

    private static Logger logger = LoggerFactory.getLogger(MyBundleTracker.class);

    public MyBundleTracker(BundleContext context, int stateMask,
                           BundleTrackerCustomizer customizer) {
        super(context, stateMask, customizer);
    }

    public Object addingBundle(Bundle bundle, BundleEvent event) {
        // Typically we would inspect bundle, to figure out if we want to
        // track it or not. If we don't want to track, then return null.
        // Otherwise return the bundle.
        print(bundle, event);
        return bundle;
    }

    private void print(Bundle bundle, BundleEvent event) {
        String symbolicName = bundle.getSymbolicName();
        String state = EnumerationDictionary.lookupState(bundle);
        String type = EnumerationDictionary.lookupEvent(event);
        System.out.println("[BT] " + symbolicName + ", state: " + state + ", event.type: " + type);
    }

    public void removedBundle(Bundle bundle, BundleEvent event,
                              Object object) {
        print(bundle, event);
    }

    public void modifiedBundle(Bundle bundle, BundleEvent event,
                               Object object) {
        print(bundle, event);
    }
}

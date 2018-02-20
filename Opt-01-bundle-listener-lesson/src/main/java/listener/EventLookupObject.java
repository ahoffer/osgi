package listener;

import static org.osgi.framework.BundleEvent.INSTALLED;
import static org.osgi.framework.BundleEvent.LAZY_ACTIVATION;
import static org.osgi.framework.BundleEvent.RESOLVED;
import static org.osgi.framework.BundleEvent.STARTED;
import static org.osgi.framework.BundleEvent.STARTING;
import static org.osgi.framework.BundleEvent.STOPPED;
import static org.osgi.framework.BundleEvent.UNINSTALLED;
import static org.osgi.framework.BundleEvent.UNRESOLVED;
import static org.osgi.framework.BundleEvent.UPDATED;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

public class EventLookupObject {

    public static String lookupEvent(BundleEvent event) {
        if (event == null) {
            return "null";
        }
        return lookupEvent(event.getType());
    }

    public static String lookupEvent(int eventType) {

        int type = eventType;
        switch (type) {
            case INSTALLED:
                return "INSTALLED";
            case LAZY_ACTIVATION:
                return "LAZY_ACTIVATION";
            case RESOLVED:
                return "RESOLVED";
            case STARTED:
                return "STARTED";
            case STARTING:
                return "STARTING";
            case STOPPED:
                return "STOPPED";
            case UNINSTALLED:
                return "UNINSTALLED";
            case UNRESOLVED:
                return "UNRESOLVED";
            case UPDATED:
                return "UPDATED";
            default:
                return "unknown event type: " + type;
        }
    }

    public static String lookupState(Bundle bundle) {
        return lookupState(bundle.getState());
    }

    private static String lookupState(int state) {
        switch (state) {
            case Bundle.ACTIVE:
                return "ACTIVE";
            case Bundle.INSTALLED:
                return "INSTALLED";
            case Bundle.RESOLVED:
                return "RESOLVED";
            case Bundle.STARTING:
                return "STARTING";
            case Bundle.STOPPING:
                return "STOPPING";
            case Bundle.UNINSTALLED:
                return "UNINSTALLED";
            default:
                return String.valueOf(state);
        }
    }
}
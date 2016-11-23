package funservice;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

import static org.osgi.framework.BundleEvent.*;

public class EventLexicon {
    public static String lookup(Bundle bundle) {
        return lookup(bundle.getState());
    }

    public static String lookup(BundleEvent event) {
        return lookup(event.getType());
    }

    public static String lookup(int eventType) {

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

}

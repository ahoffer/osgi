package providers;

import api.EnumerationDictionaryService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

import static org.osgi.framework.BundleEvent.*;

public class EnumerationDictionaryProvider implements EnumerationDictionaryService {

    public String lookupEvent(BundleEvent event) {
        if (event == null) {
            return "null";
        }
        return lookupEvent(event.getType());
    }

    public String lookupEvent(int eventType) {

        switch (eventType) {
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
                return String.valueOf(eventType);
        }
    }

    public String lookupState(Bundle bundle) {
        return lookupState(bundle.getState());
    }

    public String lookupState(int state) {
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

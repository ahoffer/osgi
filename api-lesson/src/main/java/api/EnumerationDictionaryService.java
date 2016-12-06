package api;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

public interface EnumerationDictionaryService {

    String lookupEvent(int eventType);

    String lookupEvent(BundleEvent event);

    String lookupState(int state);

    String lookupState(Bundle bundle);
}



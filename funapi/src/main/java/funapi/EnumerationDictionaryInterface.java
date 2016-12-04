package funapi;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

public interface EnumerationDictionaryInterface {

    public String lookupEvent(int eventType);

    public String lookupEvent(BundleEvent event);

    public String lookupState(int state);

    public String lookupState(Bundle bundle);
}



package funlistener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunListener implements BundleActivator, BundleListener {

    private static final Logger logger = LoggerFactory.getLogger(FunListener.class);

    public void start(BundleContext context) throws Exception {
        System.out.println("FunListener start()");
        logger.warn("FunListener register");
        context.addBundleListener(this);
    }

    public void stop(BundleContext context) throws Exception {
        logger.warn("FunListener unregister");
        context.addBundleListener(this);
    }

    public void bundleChanged(BundleEvent event) {
        logger.error("Bundle {} Event {}", event.getBundle().getSymbolicName(), EventLexicon.lookup(event));
    }
}

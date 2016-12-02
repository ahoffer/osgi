package funlogging;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {
    private static final Logger logger = LoggerFactory.getLogger(Activator.class);

    public void start(BundleContext context) throws Exception {
        logger.info("funlogging-start()");
    }

    public void stop(BundleContext context) throws Exception {
        logger.info("funlogging-stop()");
    }
}

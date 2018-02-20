package org.foo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingActivator implements BundleActivator {
    private static final Logger logger = LoggerFactory.getLogger(LoggingActivator.class);

    public void start(BundleContext context) throws Exception {
        logger.info("BUNDLE START");
    }

    public void stop(BundleContext context) throws Exception {
        logger.info("BUNDLE STOP");
    }
}

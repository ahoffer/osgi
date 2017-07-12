package org.foo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

// Test installing and uninstalling bundles.

// The error:
// Unable to resolve bundle [85](R 85.0): missing requirement [bundle [85](R 85.0)] osgi.wiring.package; (osgi.wiring.package=bundle) Unresolved requirements: [[bundle [85](R 85.0)] osgi.wiring.package; (osgi.wiring.package=bundle)]
// Meant that OSGi could not find the Activator class. I had the wrong package name for the
// Activator class in the POM file.
public class MyActivator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        System.out.println("******* My BUNDLE start() **********");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("******* MY BUNDLE stop() **********");
    }
}

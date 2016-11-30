package fun;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

// Test installing and uninstalling bundles.
/*
 * INSTALLED: The OSGi runtime knows the bundle is there.
 * RESOLVED: The bundle is there and all it’s prerequisites (dependencies) are available. The bundle can be started (or has been stopped).
 * STARTING: The bundle is being started. If it has a BundleActivator class, the BundleActivator.start() method is being executed. When done, the bundle will become ACTIVE. Note: Bundles that can be activated lazily (Bundle-ActivationPolicy: lazy) stay in this state until one of their class files is loaded.
 * ACTIVE: The bundle is running.
 * STOPPING: The bundle is being stopped. If it has a BundleActivator class, the BundleActivator.stop() method is being executed. When done, the bundle will become RESOLVED.
 * UNINSTALLED: The bundle has been removed from the OSGi runtime.
 * source: http://eclipsesource.com/blogs/2013/01/23/how-to-track-lifecycle-changes-of-osgi-bundles/
*/


// This error:
// Unable to resolve funbundle [85](R 85.0): missing requirement [funbundle [85](R 85.0)] osgi.wiring.package; (osgi.wiring.package=funbundle) Unresolved requirements: [[funbundle [85](R 85.0)] osgi.wiring.package; (osgi.wiring.package=funbundle)]
// Meant that OSGi could not find the Activator class. I had the package name wrong in the POM file.
public class Activator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        System.out.println("******* FUN BUNDLE start() **********");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("******* FUN BUNDLE stop() **********");
    }
}

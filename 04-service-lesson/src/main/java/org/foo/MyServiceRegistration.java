package org.foo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class MyServiceRegistration implements BundleActivator {

  private ServiceRegistration<?> serviceRegistration;

  public void start(BundleContext context) {
    serviceRegistration = context.registerService(Factorizer.class, new Factorizer(), null);
  }

  public void stop(BundleContext context) {
    serviceRegistration.unregister();
  }
}

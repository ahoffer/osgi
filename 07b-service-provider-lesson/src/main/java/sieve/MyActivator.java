package sieve;

import api.Factorizer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class MyActivator implements BundleActivator {

  private ServiceRegistration<?> serviceRegistration;

  public void start(BundleContext context) {
    serviceRegistration = context.registerService(Factorizer.class, new SieveProvider(), null);
  }

  public void stop(BundleContext context) {
    serviceRegistration.unregister();
  }
}

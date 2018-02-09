package bundle.trial;

import api.Factorizer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import trial.TrialDivisionProvider;

public class MyActivator implements BundleActivator {

  private ServiceRegistration<?> serviceRegistration;

  public void start(BundleContext context) {
    serviceRegistration =
        context.registerService(Factorizer.class, new TrialDivisionProvider(), null);
  }

  public void stop(BundleContext context) {
    serviceRegistration.unregister();
  }
}

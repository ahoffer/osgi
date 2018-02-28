package consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import provider.Factorizer;

public class MyServiceConsumer implements BundleActivator {

  public void start(BundleContext context) {

    // Here is how to grab the service if you are not using bluerprint
    ServiceReference<Factorizer> serviceReference = context.getServiceReference(Factorizer.class);
    Factorizer factorizer = context.getService(serviceReference);

    // Use the service
    System.out.println(factorizer.getFactors(105));
  }

  public void stop(BundleContext context) {}
}

package consumer;

import provider.Factorizer;

public class MyServiceConsumer {

  private Factorizer factorizer;

  public Factorizer getFactorizer() {
    return factorizer;
  }

  public void setFactorizer(Factorizer factorizer) {
    this.factorizer = factorizer;
  }

  public void start() {
    System.out.println(factorizer.getFactors(105));
  }
}

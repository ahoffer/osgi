package provider;

import java.util.List;
import org.apache.commons.math3.primes.Primes;

public class Factorizer {

  /**
   * Given a number, return its list of prime factors
   *
   * @param number to be factored
   * @return list of factors
   */
  public List<Integer> getFactors(Integer number) {
    return Primes.primeFactors(number);
  }
}

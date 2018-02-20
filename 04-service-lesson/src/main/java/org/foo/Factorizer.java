package org.foo;

import java.util.List;
import org.apache.commons.math3.primes.Primes;

public class Factorizer {

  public List<Integer> getFactors(Integer number) {
    return Primes.primeFactors(number);
  }
}

package impl;

import api.Factorizer;
import java.util.List;
import org.apache.commons.math3.primes.Primes;

public class FactorizerImpl implements Factorizer {

  public List<Integer> getFactors(Integer number) {
    return Primes.primeFactors(number);
  }
}

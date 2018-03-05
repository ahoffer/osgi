package sieve;

import api.Factorizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Code for factorization taken from
 * http://www.vogella.com/tutorials/JavaAlgorithmsPrimeFactorization/article.html
 */
public class SieveProvider implements Factorizer {

  @Override
  public List<Integer> getFactors(Integer number) {

    int n = number;
    List<Integer> factors = new ArrayList<>();
    for (int i = 2; i <= n; i++) {
      while (n % i == 0) {
        factors.add(i);
        n /= i;
      }
    }
    return factors;
  }
}

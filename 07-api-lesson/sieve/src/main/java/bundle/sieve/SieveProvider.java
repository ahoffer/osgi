package bundle.sieve;

import bundle.api.Factorizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Code for prime number sieve taken from https://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
 */
public class SieveProvider implements Factorizer {

  public List<Integer> getFactors(Integer number) {
    int n = number;
    List<Integer> primes = new ArrayList<>();
    // initially assume all integers are prime
    boolean[] isPrime = new boolean[n + 1];
    for (int i = 2; i <= n; i++) {
      isPrime[i] = true;
    }

    // mark non-primes <= n using Sieve of Eratosthenes
    for (int factor = 2; factor * factor <= n; factor++) {

      // if factor is prime, then mark multiples of factor as nonprime
      // suffices to consider mutiples factor, factor+1, ...,  n/factor
      if (isPrime[factor]) {
        for (int j = factor; factor * j <= n; j++) {
          isPrime[factor * j] = false;
        }
      }
    }

    // count primes
    for (int i = 2; i <= n; i++) {
      if (isPrime[i]) {
        primes.add(i);
      }
    }

    return primes;
  }
}

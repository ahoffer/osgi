package api;

import java.util.List;

public interface Factorizer {
  /**
   * Given a number, return its list of prime factors
   *
   * @param number to be factored
   * @return list of factors
   */
  List<Integer> getFactors(Integer number);
}

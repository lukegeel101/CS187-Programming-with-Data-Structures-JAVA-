package algorithms;

/** A class that implements some basic mathematical functions using recursion */
public class RecursiveMath {

  /**
   * Returns {@code true} if {@code val} is even and {@code false} otherwise. A number is even if it
   * is zero or if its predecessor is an odd number. A negative number is even if its negation is
   * even.
   *
   * @param val
   * @return {@code true} if {@code val} is even and {@code false} otherwise.
   */
  public boolean isEven(int val) {
    // TODO: Implement the isEven method
    // Without recursion this could be: return val % 2 == 0;
    return false;
  }

  /**
   * Returns {@code true} if {@code val} is odd and {@code false} otherwise. Zero is not odd, but
   * one is odd, as is any number whose predecessor is even. A negative number is odd if its
   * negation is odd.
   *
   * @param val
   * @return {@code true} if {@code val} is odd and {@code false} otherwise.
   */
  public boolean isOdd(int val) {
    // TODO: Implement the isOdd method
    // Without recursion this could be: return val % 2 == 1;
    return false;
  }

  /**
   * Returns the sum of all values between 1 and n.
   *
   * @param n
   * @return the sum of all values between 0 and n.
   * @throws IllegalArgumentException if n is less than 0.
   */
  public int sumN(int n) {
    // TODO: Implement the sumN method
    return -1;
  }

  /**
   * Returns the multiplication of all values between 1 and n.
   *
   * @param n
   * @return the multiplication of all values between 1 and n.
   * @throws IllegalArgumentException if n is less than 0.
   */
  public int factorial(int n) {
    // TODO: Implement the factorial method
    return -1;
  }

  /**
   * Returns 2 to the nth power. (2^n)
   *
   * @param n
   * @return 2 to the nth power.
   * @throws IllegalArgumentException if n is less than 0.
   */
  public int biPower(int n) {
    // TODO: Implement the biPower method
    return -1;
  }
}

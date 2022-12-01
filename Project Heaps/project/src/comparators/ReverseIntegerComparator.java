package comparators;
import java.util.Comparator;
/**
 * An {@link ReverseIntegerComparator} compares integers in reverse order, e.g., compare(2, 1)
 * returns a negative number.
 */
public class ReverseIntegerComparator implements Comparator<Integer> {
  @Override
  public int compare(Integer arg0, Integer arg1) {
    // TODO: Implement the compare() method
    Integer a = arg0;
    Integer b = arg1;

    if (a > b) {
      return -1;
    }
		if (a < b) {
      return 1;
    }
		return a.compareTo(b);
  }
}
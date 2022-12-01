package comparators;

import java.util.Comparator;

/** An {@link IntegerComparator} compares integers in the natural way. */
public class IntegerComparator implements Comparator<Integer> {
  @Override
  public int compare(Integer arg0, Integer arg1) {
    // TODO: Implement the compare() method
    Integer a = arg0;
    Integer b = arg1;

    if (a < b) {
      return -1;
    }
		if (a > b) {
      return 1;
    } 
		return a.compareTo(b);
  }
}
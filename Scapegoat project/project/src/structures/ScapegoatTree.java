package structures;
public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
  private int upperBound;
  //declare private variable upperBound

	@Override
	public void add(T element) {
		upperBound = upperBound + 1;
		double LogLimit = ((Math.log10(upperBound)) / (Math.log10(3/2)));
		if (LogLimit < upperBound){
		}
  }

  @Override
  //remove 
	public boolean remove(T t) {
    if (t == null) 
    //check if t = null
    {
			throw new NullPointerException(); //throw exception
    }
    //throw exception
    boolean result = contains(t);
    //check it result contains t
		if (result) {
    }
    //checks if upperbound is greater than twice the size
		if (upperBound > 2 * size()) {
      balance();
      //balace
			upperBound = size();
		}
		return result;
	}
}
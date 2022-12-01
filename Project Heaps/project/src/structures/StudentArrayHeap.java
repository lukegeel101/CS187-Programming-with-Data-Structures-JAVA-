package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
  // TODO: Implement all abstract methods from AbstractArrayHeap.
  public StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
  }
  
  @Override
	public int getRightChildOf(int index) {
    int rChild = (index * 2) + 2;
		if (index < 0) {
      throw new IndexOutOfBoundsException();
    }
		return rChild;
	}

	@Override
	public int getLeftChildOf(int index) {
    int lChild = (index * 2) + 1;
		if (index < 0) {
      throw new IndexOutOfBoundsException();
    }
		return lChild;
	}

	@Override
	public int getParentOf(int index) {
    int parent = (index - 1) / 2;
		if (index < 1) {
      throw new IndexOutOfBoundsException();
    }
		return parent;
	}

	@Override
	protected void bubbleUp(int index) {
		Entry<P, V> elem = heap.get(index);
		int parent = (index - 1) / 2;
		while ((index > 0) && getComparator().compare(elem.getPriority(), heap.get(parent).getPriority()) > 0) {
			heap.set(index, heap.get(parent));
			index = parent;
      parent--;
      parent = parent /2;
		}
		heap.set(index, elem);	
	}

	@Override
	protected void bubbleDown(int index) {
		Entry<P, V> elem = heap.get(0);
    int lChild, hole = 0, left = 1, right = 2;
    int hSize = heap.size();
		while (left <= hSize - 1) {
			if (right <= hSize - 1 && getComparator().compare(heap.get(left).getPriority(), heap.get(right).getPriority()) < 0)
				lChild = right; 
			else {
				lChild = left;
      }
			if (getComparator().compare(elem.getPriority(), heap.get(lChild).getPriority()) >= 0)
				break;
			heap.set(hole, heap.get(lChild));
			hole = lChild;
			left = (hole * 2) + 1;
			right = left + 1;
		}
		heap.set(hole, elem);
	}	
}
package structures;

import comparators.IntegerComparator;
import java.util.Iterator;
import java.util.Comparator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {
  // TODO: Implement all abstract methods from PriorityQueue.
  StudentArrayHeap<Integer, V> heap;
	
	public MaxQueue() {
		IntegerComparator comparator = new IntegerComparator();
		heap = new StudentArrayHeap<Integer, V>(comparator);
	}
	
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		heap.add(priority, value);
		return this;
  }
  
  @Override
	public V peek() {
		if (heap.isEmpty())
			throw new IllegalStateException();
		return heap.peek();
	}

	@Override
	public V dequeue() {
		return heap.remove();
	}
  
	@Override
	public int size() {
		return heap.size();
  }
  
	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		return heap.asList().iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		return heap.getComparator();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}
}
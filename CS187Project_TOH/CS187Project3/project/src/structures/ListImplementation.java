package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImplementation<T> implements ListInterface<T> {
	private Node<T> head;
	private Node<T> last;
	private int size;

	public ListImplementation() {
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public ListImplementation<T> append(T elem) {
		if (elem == null) throw new NullPointerException();
		Node<T> value = new Node<T>(elem, null);
		if (!isEmpty()) last.setNext(value);
		else head = value;
		last = value;
		size++;
		return this;
	}

	@Override
	public T get(int n) {
		Iterator<T> iter = iterator();
		if (n < 0 || n > size) throw new NoSuchElementException();
		while (n > 0) {
			iter.next();
			n--;
		}
		return iter.next();
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(head);
	}
}
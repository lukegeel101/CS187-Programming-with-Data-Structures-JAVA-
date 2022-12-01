package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@code ListImplementation} is a Linked List that wraps {@link Node}s and provides useful
 * operations.
 */
public class ListImplementation<T> implements ListInterface<T> {
  // TODO: Add the sensible instance variables for a linked list implementation.
  public ListImplementation() {
    // TODO: Initialize instance variables.
  }

  /** Returns the number of nodes in this list. */
  @Override
  public int size() {
    // TODO: return the size of the list.
    return -1;
  }

  @Override
  public boolean isEmpty() {
    // TODO: Return true if the list is empty; false otherwise
    return false;
  }

  /**
   * Appends {@code elem} to the end of this {@code ListImplementation} and returns itself for
   * convenience.
   */
  @Override
  public ListImplementation<T> append(T elem) {
    // TODO: Append an item to the list
    return null;
  }

  /** Gets the {@code n}th element from this list. */
  @Override
  public T get(int n) {
    // TODO: Get an item from the list at the given index.
    return null;
  }

  /**
   * Returns an iterator over this list. The iterator does not support the {@code remove()} method.
   */
  @Override
  public Iterator<T> iterator() {
    // TODO: Return an iterator over this list.
    return null;
  }
}

package structures;

public class DoublyCircularLinkedListImplementation<T extends Comparable<T>>
    implements DoublyCircularLinkedList<T> {
      private DLLNode<T> previous;
      private int listSize;
      private DLLNode<T> list;
      private boolean found;
      private DLLNode<T> location;
      private DLLNode<T> currentPos;

  public DoublyCircularLinkedListImplementation() {
    listSize = 0;
		list = null;
		location = null;
		previous = null;
		currentPos = null;
		found = false;
  }

  @Override
  public int size() {
    return listSize - 1;
  }

  @Override
  public void add(T element) {
    DLLNode<T> newNode = new DLLNode<T>(element);
		if (list == null){
			list = newNode;
			newNode.setForward(list);
			newNode.setBack(list);
		}
		else{
			newNode.setForward(list);
			newNode.setBack(list.getBack());
			list.getBack().setForward(newNode);
			list.setBack(newNode);
		}
		listSize++;
  }

  @Override
  public boolean remove(T element) {
    return false;
  }

  @Override
  public boolean contains(T element) {
    return found;
  }

  @Override
  public T get(T element) {
    return location.getInfo();
  }

  @Override
  public void reset() {
    currentPos = list;
  }

  @Override
  public T getNext() {
    if(currentPos == null){
			return null;
		}
		else{
			T nextElem = currentPos.getInfo();
			currentPos = currentPos.getForward();
			return nextElem;
		}
  }

  @Override
  public T getPrevious() {
    if(currentPos == null){
			return null;
		}
		else{
			T prevElem = currentPos.getBack().getInfo();
			currentPos = currentPos.getBack();
			return prevElem;
		}
  }
}
package stack;

public class LinkedStack<T> implements StackInterface<T> {

	private LLNode<T> top;
	private int size;

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T ret = top.getData();
			top = top.getNext();
			size = size - 1;
			return ret;
		}
		throw new StackUnderflowException("pop from empty");
	}

	@Override
	public T top() throws StackUnderflowException {
		if (!isEmpty()){
			return top.getData();
		}
		throw new StackUnderflowException("top of empty stack");
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void push(T elem) {
		LLNode<T> num = new LLNode<T>(elem);
		num.setNext(top);
		top = num;
		size = size + 1;
	}

}
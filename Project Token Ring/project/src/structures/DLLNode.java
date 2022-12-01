// ----------------------------------------------------------------------------
// DLLNode.java              by Dale/Joyce/Weems                     Chapter 7
//
// Implements <T> nodes for a doubly linked list.
// ----------------------------------------------------------------------------

package structures;

public class DLLNode<T> {
  private DLLNode<T> back;
  private DLLNode<T> forward;
  private T info;

  public DLLNode(T info) {
    this.info = info;
    this.back = null;
    this.forward = null;
  }

  // Sets info of this LLNode.
  public void setInfo(T info) {
    this.info = info;
  }

  // Returns info of this DLLNode.
  public T getInfo() {
    return info;
  }

  // Sets back link of this DLLNode.
  public void setBack(DLLNode<T> back) {
    this.back = back;
  }

  // Returns back link of this DLLNode.
  public DLLNode<T> getBack() {
    return back;
  }

  // Sets forward link of this DLLNode;
  public void setForward(DLLNode<T> forward) {
    this.forward = forward;
  }

  // Returns forward link of this DLLNode.
  public DLLNode<T> getForward() {
    return forward;
  }
}

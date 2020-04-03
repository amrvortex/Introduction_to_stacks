package eg.edu.alexu.csd.datastructure.stack.cs52;

public class Stack implements IStack {
	private class Node{
		private Object element;
		private Node next;
		Node(Object e){
			this.element=e;
			}
		}
	
	private Node top;
	private int size;
	
	Stack(){
		this.top = null;
		this.size = 0;
	}
	
	@Override
	/**
	* Removes the element at the top of stack and returns that element.
	*
	* @return top of stack element, or through exception if empty
	*/
	public Object pop(){
		if(isEmpty()){
			throw new RuntimeException("The Stack is Empty");
		}else{
		Object temp = top.element;
		top = top.next;
		size--;
		return temp;
		}
	}

	@Override
	/**
	* Get the element at the top of stack without removing it from stack.
	*
	* @return top of stack element, or through exception if empty
	*/
	public Object peek() {
		if(isEmpty()){
			throw new RuntimeException("The Stack is Empty");
		}else{
			Object temp = top.element;
			return temp;
		}
	}

	@Override
	/**
	* Pushes an item onto the top of this stack.
	*
	* @param object
	* to insert
	*/
	public void push(Object element){
		Node n = new Node(element);
		n.next = top;
		top = n;
		size++;
	}

	@Override
	/**
	* Tests if this stack is empty
	*
	* @return true if stack empty
	*/
	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

	@Override
	/**
	* Returns the number of elements in the stack.
	*
	* @return number of elements in the stack
	*/

	public int size() {
		return size;
	}
	
}

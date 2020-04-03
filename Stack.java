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
	public Object peek() {
		if(isEmpty()){
			throw new RuntimeException("The Stack is Empty");
		}else{
			Object temp = top.element;
			return temp;
		}
	}

	@Override
	public void push(Object element){
		Node n = new Node(element);
		n.next = top;
		top = n;
		size++;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

	@Override
	public int size() {
		return size;
	}
	
}

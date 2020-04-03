package eg.edu.alexu.csd.datastructure.stack.cs52;

import java.util.Stack;

public class Stack_UI {
 public static void main(String[] args){
	Stack stack = new Stack();
	stack.push('v');
	stack.push("vortex");
	stack.push(0);
	stack.push(10);
	System.out.println(stack.isEmpty());
	System.out.println(stack.size());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.peek());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.size());
	System.out.println(stack.isEmpty());
 }
}

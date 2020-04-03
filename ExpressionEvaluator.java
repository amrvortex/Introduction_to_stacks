package eg.edu.alexu.csd.datastructure.stack.cs52;

public class ExpressionEvaluator implements IExpressionEvaluator{

	@Override
	public String infixToPostfix(String expression){
		//making a stringbuffer to be able to change it through the operations
		StringBuffer str = new StringBuffer(expression);
		for (int i = 0; i < str.length(); i++){
			//handling if the number was negative
			if(str.charAt(i) == '-'){
				int j = i-1;
				while(j>=0 && str.charAt(j) == ' ')j++;
				if((j>=0&&!Character.isDigit(str.charAt(j))&&!ifCharacter(str.charAt(j))&&str.charAt(j)!=')')||i==0){
					str.insert(i,"(0");
					i += 3;
					while(str.charAt(i) == ' ')i++;
					while (i<str.length()&&(Character.isDigit(str.charAt(i))||ifCharacter(str.charAt(i))))i++;
					str.insert(i,')');
					i++;
				}
			}
			if(i<str.length() && (ifCharacter(str.charAt(i)) || str.charAt(i) == ')')){
				int j = i+1;
				while(j < str.length() && str.charAt(j) == ' ')j++;
				if(j < str.length() && (str.charAt(j) =='(' || ifCharacter(str.charAt(j))))
					str.insert(j,'*');
			}
		}
		String strExpression="";  // a string to store the postfix expression
		Stack operatorStack = new Stack();         // a stack to store the operators and results
		Stack parenthStack = new Stack();		// a stack to store the parenthesis
		for (int i = 0; i < str.length(); i++) {
			if(ifCharacter(str.charAt(i)) || Character.isDigit(str.charAt(i)) || str.charAt(i)=='.' || str.charAt(i) == ' ')
				strExpression += str.charAt(i);
			else if (str.charAt(i) == '('){
				parenthStack.push('(');
				int temp = ++i;
				// to check if the number of parenthesis is correct or not 
				while(i<str.length() && !parenthStack.isEmpty()){
					if(str.charAt(i) == '(')
						parenthStack.push('(');
					else if (str.charAt(i) == ')')
						parenthStack.pop();
					i++;
				}
				if(parenthStack.isEmpty())
					strExpression += infixToPostfix(str.substring(temp, --i));
				else
					throw new RuntimeException("the number of parenthesis isn't sufficient");
			}
			else if (checkOperator(str.charAt(i))){
				strExpression += ' ';
				while(!operatorStack.isEmpty() && !precedence(str.charAt(i),(char)operatorStack.peek())){
					strExpression += operatorStack.pop();
					strExpression += ' ';
				}
				operatorStack.push(str.charAt(i));
			}
			else
				throw new RuntimeException("the input has invalid parameters");
		}
		while(!operatorStack.isEmpty())
			strExpression += " " +operatorStack.pop();
		
		return strExpression;
	}

	@Override
	public int evaluate(String expression){
		float number;
		Stack numbers = new Stack();
		for (int i = 0; i < expression.length(); i++){
			if(Character.isDigit(expression.charAt(i))){
				number = expression.charAt(i) - '0';
				while(Character.isDigit(expression.charAt(i)) && Character.isDigit(expression.charAt(i+1))){
					number *= 10;
					i++;
					number += expression.charAt(i) - '0';
				}
				if(expression.charAt(i+1) == '.' || expression.charAt(i+1) == ','){
					i += 2;
					int temp = 10;
					while (Character.isDigit(expression.charAt(i))){
						number += (float)(expression.charAt(i)-'0')/temp;
						i++;
						temp *= 10;
					}
				}
				numbers.push(number);
			}
			else if (checkOperator(expression.charAt(i))){
				float f2 =(float)numbers.pop();
				float f1 =(float)numbers.pop();
				numbers.push(evaluateFloat(f1, f2, expression.charAt(i)));
			}
			else if (expression.charAt(i)!=' ')
				throw new RuntimeException ("the input have invalid parameters");
		}
		if(numbers.size() != 1)
			throw new RuntimeException("the postfix of the input is invalid");
		return  (int)(float)numbers.pop();
	}
	
	private float evaluateFloat(float f1 , float f2 , char operator){
		switch (operator) {
		case '+': return f1+f2;
		case '-': return f1-f2;
		case '*': return f1*f2;
		default:{
			if(f2 == 0)
			throw new IllegalArgumentException("Can't divide by zero");
			else
				return f1/f2;
		       }
		}
	}
	private boolean checkOperator(char c){
		if(c == '*'|| c == '+' || c == '-' || c == '/')
			return true;
		else
			return false;
	}
	private boolean precedence(char c1, char c2){
		if((c1 =='*'|| c1 == '/') && (c2 =='+' || c2 =='-'))
			return true;
		else
			return false;
	}
	public boolean ifCharacter(char c){
		if((c >='a' && c <='z') || (c >='A' && c <='Z'))
			return true;
		else
			return false;
	}
}

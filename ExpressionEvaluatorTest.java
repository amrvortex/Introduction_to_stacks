package eg.edu.alexu.csd.datastructure.stack.cs52;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionEvaluatorTest {

	@Test
	void infixToPostfixTest(){
		ExpressionEvaluator exp = new ExpressionEvaluator();
		
		//test1(without parenthesis)
		String expected ="a b +";
		String actual = exp.infixToPostfix("a+b");
		assertEquals(expected, actual);
		
		//test2(without parenthesis)
		expected = "3 5 * 4 +";
		actual = exp.infixToPostfix("3*5+4");
		assertEquals(expected, actual);
		
		//test3(without parenthesis)
		expected = "a 5 * b +";
		actual = exp.infixToPostfix("a*5+b");
		assertEquals(expected, actual);
		
		//test1(with parenthesis)
		expected="a b c * -";
		actual=exp.infixToPostfix("a-(bc)");
		assertEquals(expected, actual);
		
		//test2(with parenthesis)
		expected="x 5 + 3 *";
		actual= exp.infixToPostfix("(x+5)*3");
		assertEquals(expected, actual);
		
		//test3(with parenthesis)
		expected="a 2 + b *";
		actual=exp.infixToPostfix("(a+2)*b");
		assertEquals(expected, actual);
		
		//test1(with negative numbers)
		expected="0 a - b +";
		actual=exp.infixToPostfix("-a+b");
		assertEquals(expected, actual);
		
		//test2(with negative numbers)
		expected="a 2 + b 5 * * c 6 / *";
		actual = exp.infixToPostfix("(a+2)(b*5)(c/6)");
		assertEquals(expected, actual);
		
		//test3(with negative numbers)
		expected = "8 2 * 0 6 - +";
		actual = exp.infixToPostfix("8*2+-6");
		assertEquals(expected, actual);
		
		//test4(with negative numbers)
		expected = "0 10 -";
		actual = exp.infixToPostfix("-10");
		assertEquals(expected, actual);
		//test for null input
		expected = "";
		actual = exp.infixToPostfix("");
		assertEquals(expected, actual);
	}
	
	@Test
	void evaluateTest(){
		ExpressionEvaluator exp = new ExpressionEvaluator();
		//Test1
		int expected = 8;
		int actual = exp.evaluate(exp.infixToPostfix("5+3"));
		assertEquals(expected, actual);
		
		//Test2
		expected = -3;
		actual = exp.evaluate(exp.infixToPostfix("5-8"));
		assertEquals(expected, actual);
		
		//Test3
		expected = 70 ;
		actual = exp.evaluate(exp.infixToPostfix("(5*7)(6-4)"));
		assertEquals(expected, actual);
	}

}

package evaluator.arith;

import parser.IllegalPostfixExpressionException;
import language.Operand;
import stack.LinkedStack;
import parser.PostfixParser.Type;
import evaluator.PostfixEvaluator;
import language.Operator;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.StackInterface;

public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;

	public ArithPostfixEvaluator() {
		stack = new LinkedStack<>();
	}

	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch (type) {
				case OPERAND:
					stack.push(token.getOperand());
					break;
				case OPERATOR:
					Operator<Integer> op = token.getOperator();
					if (op.getNumberOfArguments() == 1){
						op.setOperand(0, stack.pop());
					}
					else {
						op.setOperand(1, stack.pop());
						op.setOperand(0, stack.pop());
					}
				Operand<Integer> val = op.performOperation();
				stack.push(val);
				break;
				default:
					throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}
		}
		Integer result = stack.pop().getValue();
		if (!stack.isEmpty()) throw new IllegalPostfixExpressionException();
		return result;
	}

}

package ExpressionsAST;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

public class ASTBuilder {
	private final String expression;
	private Queue<String> evalQueue;

	public ASTBuilder(String expression) {
		this.expression = expression;
	}

	public Expression build() throws InvalidExpressionException {
		generateEvalQueue();

		Stack<Expression> computeStack = new Stack<>();
		char operator;

		String item;
		while (!evalQueue.isEmpty()) {
			item = evalQueue.poll();

			while (!isOperator(item)) {
				computeStack.push(Number.parseInt(item));
				item = evalQueue.remove();
			}
			operator = item.charAt(0);

			try {
				computeStack.push(bind(computeStack.pop(), operator, computeStack.pop()));
			} catch (EmptyStackException e) {
				throw new InvalidExpressionException("Excess operator in expression.");
			}
		}

		return computeStack.pop();
	}

	private Expression bind(Expression left, char operator, Expression right) {
		switch (operator) {
			case '<':
				return new Relation(left, Relation.Op.LESS, right);
			case '>':
				return new Relation(left, Relation.Op.GREATER, right);
			case '=':
				return new Relation(left, Relation.Op.EQUAL, right);
			case '+':
				return new Term(left, Term.Op.ADD, right);
			case '-':
				return new Term(right, Term.Op.SUB, left);
			case '*':
				return new Factor(left, right);
			default:
				return null;
		}
	}

	private void generateEvalQueue() throws InvalidExpressionException {
		Stack<Character> operators = new Stack<>();
		evalQueue = new LinkedList<>();

		ByteArrayInputStream fromString = new ByteArrayInputStream(expression.getBytes());
		Tokenizer tokenizer = new Tokenizer(fromString);
		try {
			tokenizer.tokenize();
		} catch (IOException ignored) {
		}
		List<String> tokens = tokenizer.getTokens();

		boolean wasNumeric = false;
		for (String token : tokens) {
			if (isNumeric(token)) {
				if (wasNumeric) {
					throw new InvalidExpressionException("Two numbers in a row with no operator between.");
				}
				evalQueue.add(token);
				wasNumeric = true;
				continue;
			}
			wasNumeric = false;

			if (isOperator(token)) {
				while (!operators.empty()) {
					if (isOperator(operators.peek())) {
						if (operatorPriority(token) <= operatorPriority(operators.peek())) {
							evalQueue.add(operators.pop().toString());
						} else break;
					} else break;
				}
				operators.push(token.charAt(0));

				continue;
			}

			if (token.charAt(0) == '(') {
				operators.push(token.charAt(0));
				continue;
			}

			if (token.charAt(0) == ')') {
				boolean handled = false;

				while (!operators.empty()) {
					if (operators.peek() != '(') {
						evalQueue.add(operators.pop().toString());
					} else {
						operators.pop();
						handled = true;
						break;
					}
				}

				if (!handled) {
					throw new InvalidExpressionException("Missing opening bracket or excess closer bracket.");
				}
			}

		}

		while (!operators.empty()) {
			if (operators.peek() == '(') {
				throw new InvalidExpressionException("Excess opening bracket or missing closing bracket.");
			}
			evalQueue.add(operators.pop().toString());
		}
	}

	private int operatorPriority(char operator) {
		switch (operator) {
			case '<':
			case '>':
			case '=':
				return 1;
			case '+':
			case '-':
				return 2;
			case '*':
				return 3;
			default:
				return 0;
		}
	}

	private int operatorPriority(CharSequence seq) {
		return operatorPriority(seq.charAt(0));
	}

	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isOperator(CharSequence str) {
		return "<>=+-*".contains(str);
	}

	private boolean isOperator(char c) {
		return isOperator(String.valueOf(c));
	}
}

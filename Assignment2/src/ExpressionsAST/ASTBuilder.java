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

		// Convert generated reverse polish notation (RPN) into tree

		Stack<Expression> computeStack = new Stack<>();
		char operator;

		// While RPN queue is not empty, process it
		String item;
		while (!evalQueue.isEmpty()) {
			item = evalQueue.poll();

			// If item is number, create a node of it
			// and push it to the computing stack
			while (!isOperator(item)) {
				computeStack.push(Number.parseInt(item));
				item = evalQueue.remove();
			}
			operator = item.charAt(0);

			// If it is an operator, pick Number nodes from stack
			// and bind them to the new node
			try {
				computeStack.push(bind(computeStack.pop(), operator, computeStack.pop()));
			} catch (EmptyStackException e) {
				// If there are no enough numbers in the stack
				// it means that excess operator appeared in the expression
				throw new InvalidExpressionException("Excess operator in expression.");
			}
		}

		return computeStack.pop();
	}

	private Expression bind(Expression left, char operator, Expression right) {
		// Create new node for tree
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
		// Apply shunting yard algorithm to
		// create a reverse polish notation

		Stack<Character> operators = new Stack<>();
		evalQueue = new LinkedList<>();

		// Tokenize input expression
		ByteArrayInputStream fromString = new ByteArrayInputStream(expression.getBytes());
		Tokenizer tokenizer = new Tokenizer(fromString);
		try {
			tokenizer.tokenize();
		} catch (IOException ignored) {
		}
		List<String> tokens = tokenizer.getTokens();

		// Process tokens
		boolean wasNumeric = false;
		for (String token : tokens) {
			// If token is numeric
			// add it to the output queue
			if (isNumeric(token)) {
				if (wasNumeric) {
					// If two numerics appeared in a row
					// it means that expression is incorrect
					throw new InvalidExpressionException("Two numbers in a row with no operator between.");
				}
				evalQueue.add(token);
				wasNumeric = true;
				continue;
			}
			wasNumeric = false;

			if (isOperator(token)) {
				// If token is an operator
				while (!operators.empty()) {
					// If an operator appeared in operator stack
					// take it and push two operators according to their priority
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
					throw new InvalidExpressionException("Missing opening bracket or excess closing bracket.");
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

package ExpressionsAST;

import SimpleTokenizer.Tokenizer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ASTBuilder {
	private String expression;
	private Queue<String> evalQueue;

	public ASTBuilder(String expression) {
		this.expression = expression;
	}

	private void generateEvalQueue() {
		if (evalQueue != null) {
			return;
		}

		Stack<Character> operators = new Stack<>();
		evalQueue = new LinkedList<>();

		ByteArrayInputStream fromString = new ByteArrayInputStream(expression.getBytes());
		Tokenizer tokenizer = new Tokenizer(fromString);
		try {
			tokenizer.tokenize();
		} catch (IOException ignored) {
		}
		List<String> tokens = tokenizer.getTokens();

		for (String token : tokens) {
			if (isNumeric(token)) {
				evalQueue.add(token);
				continue;
			}

			while (!operators.empty()) {
				//
			}
		}
	}

	private int operPriority(char oper) {
		switch (oper) {
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

	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}

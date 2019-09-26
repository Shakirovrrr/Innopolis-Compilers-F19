package ExpressionsAST;

public class InvalidExpressionException extends Exception {
	public InvalidExpressionException() {
		super();
	}

	InvalidExpressionException(String message) {
		super(message);
	}
}

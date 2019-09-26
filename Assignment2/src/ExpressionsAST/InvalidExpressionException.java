package ExpressionsAST;

public class InvalidExpressionException extends Exception {
	public InvalidExpressionException() {
	}

	InvalidExpressionException(String message) {
		System.out.println(message);
	}
}

package ExpressionsAST;

public interface Expression {
	long eval();

	static Expression fromString(String str) throws InvalidExpressionException {
		ASTBuilder builder = new ASTBuilder(str);
		return builder.build();
	}
}

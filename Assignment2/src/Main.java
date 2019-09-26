import ExpressionsAST.ASTBuilder;
import ExpressionsAST.Expression;
import ExpressionsAST.InvalidExpressionException;

class Main {
	public static void main(String[] args) throws InvalidExpressionException {
		String expr = "1 + (26 - 98) * 15 + 777";

		ASTBuilder builder = new ASTBuilder(expr);
		Expression tree = builder.build();

		System.out.println(tree.eval());
	}
}

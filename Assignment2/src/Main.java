import ExpressionsAST.Expression;
import ExpressionsAST.InvalidExpressionException;

class Main {
	public static void main(String[] args) throws InvalidExpressionException {
		String expr = "1 + (26 - 98) * 15 + 777";
		Expression tree = Expression.fromString(expr);
		System.out.println(tree.eval());
	}
}

package ExpressionsAST;

public class Parenthesized extends Primary {
	private Expression expr;

	public Parenthesized(Expression expr) {
		this.expr = expr;
	}

	@Override
	public long eval() {
		return expr.eval();
	}
}

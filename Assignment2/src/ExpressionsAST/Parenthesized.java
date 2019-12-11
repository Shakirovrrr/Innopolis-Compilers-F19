package ExpressionsAST;

class Parenthesized extends Primary {
	private final Expression expr;

	public Parenthesized(Expression expr) {
		this.expr = expr;
	}

	@Override
	public long eval() {
		return expr.eval();
	}
}

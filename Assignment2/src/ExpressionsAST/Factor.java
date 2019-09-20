package ExpressionsAST;

public class Factor extends Binary {
	private Primary left;
	private Factor right;

	public Factor(Primary left, Factor right) {
		this.left = left;
		this.right = right;
	}

	public Factor(Primary primary) {
		this.left = primary;
		this.right = new Factor(new Primary(1));
	}

	@Override
	public long eval() {
		return left.eval() * right.eval();
	}
}

package ExpressionsAST;

class Factor extends Binary {
	//private Primary left;
	//private Factor right;

	Factor(Primary left, Factor right) {
		this.left = left;
		this.right = right;
	}

	Factor(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	Factor(Primary primary) {
		this.left = primary;
	}

	@Override
	public long eval() {
		if (right != null) {
			return left.eval() * right.eval();
		} else {
			return left.eval();
		}
	}
}

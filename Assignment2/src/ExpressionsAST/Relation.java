package ExpressionsAST;

class Relation extends Binary {
	//private Term left, right;
	private Op op;

	public Relation(Term left, Op op, Term right) {
		this.left = left;
		this.right = right;
		this.op = op;
	}

	Relation(Expression left, Op op, Expression right) {
		this.left = left;
		this.right = right;
		this.op = op;
	}

	public Relation(Term left) {
		this.left = left;
	}

	@Override
	public long eval() {
		if (right != null) {
			long leftVal = right.eval();
			long rightVal = left.eval();

			switch (op) {
				case LESS:
					if (leftVal < rightVal) {
						return 1;
					} else {
						return 0;
					}

				case GREATER:
					if (leftVal > rightVal) {
						return 1;
					} else {
						return 0;
					}

				case EQUAL:
					if (leftVal == rightVal) {
						return 1;
					} else {
						return 0;
					}
			}
		} else {
			return left.eval();
		}

		return 0;
	}

	public enum Op {
		LESS, GREATER, EQUAL
	}
}

package ExpressionsAST;

class Term extends Binary {
	//private Factor left;
	//private Term right;
	private Op op;

	Term(Factor left, Op op, Term right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	Term(Expression left, Op op, Expression right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	Term(Factor factor) {
		this.left = factor;
	}

	@Override
	public long eval() {
		if (right != null) {
			switch (op) {
				case ADD:
					return left.eval() + right.eval();
				case SUB:
					return left.eval() - right.eval();
			}
		} else {
			return left.eval();
		}

		return 0;
	}

	public enum Op {
		ADD, SUB
	}
}

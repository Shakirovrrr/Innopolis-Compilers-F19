package ExpressionsAST;

public class Relation extends Binary {
	private Term left, right;
	private Op op;

	public Relation(Term left, Op op, Term right) {
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
			long lval = left.eval();
			long rval = right.eval();

			switch (op) {
				case LESS:
					if (lval < rval) {
						return 1;
					} else {
						return 0;
					}

				case GREATER:
					if (lval > rval) {
						return 1;
					} else {
						return 0;
					}

				case EQUAL:
					if (lval == rval) {
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

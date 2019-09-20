package ExpressionsAST;

class Number extends Primary {
	private long val;

	public Number(long val) {
		this.val = val;
	}

	@Override
	public long eval() {
		return val;
	}
}

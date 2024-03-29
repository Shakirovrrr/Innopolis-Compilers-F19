package ExpressionsAST;

class Number extends Primary {
	private final long val;

	@SuppressWarnings("WeakerAccess")
	Number(long val) {
		this.val = val;
	}

	static Number parseInt(String str) {
		return new Number(Integer.parseInt(str));
	}

	@Override
	public long eval() {
		return val;
	}
}

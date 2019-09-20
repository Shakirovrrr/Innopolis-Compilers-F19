package ExpressionsAST;

public class Number extends Primary {
	private long val;

	public Number(long val) {
		super(null);
		this.val = val;
	}

	@Override
	public long eval() {
		return val;
	}
}

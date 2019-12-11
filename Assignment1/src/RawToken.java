public class RawToken {
	public String val;
	public int line, place;

	public RawToken(String val, int line, int place) {
		this.val = val;
		this.line = line;
		this.place = place;
	}

	public RawToken(char val, int line, int place) {
		this.val = String.valueOf(val);
		this.line = line;
		this.place = place;
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class Tokenizer {
	private BufferedReader reader;
	private List<String> tokens;

	public Tokenizer(InputStream stream) {
		InputStreamReader inputReader = new InputStreamReader(stream);
		this.reader = new BufferedReader(inputReader);

		this.tokens = new LinkedList<>();
	}

	public void tokenize() throws IOException {
		int readByte = reader.read();
		char read;
		StringBuilder tokenBuf = new StringBuilder();
		states state = states.WORD, prevState = states.WORD;

		while (readByte != -1) {
			read = (char) readByte;
			do {
				if (read == ' ' || read == '\n') {
					flushTokenBuf(tokenBuf);
					break;
				}

				if (Character.isLetterOrDigit(read) || read == '_') {
					state = states.WORD;

					if (prevState != states.WORD) {
						flushTokenBuf(tokenBuf);
					}

					tokenBuf.append(read);
					break;
				}

				if (";,{}+-".contains(String.valueOf(read))) {
					state = states.OTHER;
					flushTokenBuf(tokenBuf);
					tokens.add(String.valueOf(read));
					break;
				}

				if (read == ':') {
					state = states.COLON;
					flushTokenBuf(tokenBuf);
					tokenBuf.append(read);
					break;
				}

				if (read == '=') {
					state = states.EQ;
					if (prevState == states.COLON) {
						state = states.ASSIGN;
						tokenBuf.append(read);
						flushTokenBuf(tokenBuf);
					} else if (prevState == states.LT) {
						state = states.LE;
						tokenBuf.append(read);
						flushTokenBuf(tokenBuf);
					} else if (prevState == states.GT) {
						state = states.GE;
						tokenBuf.append(read);
						flushTokenBuf(tokenBuf);
					} else {
						flushTokenBuf(tokenBuf);
						tokens.add(String.valueOf(read));
					}
					break;
				}


				if (read == '<') {
					state = states.LT;
					flushTokenBuf(tokenBuf);
					tokenBuf.append(read);
					break;
				}

				if (read == '>') {
					state = states.GT;
					if (prevState == states.LT) {
						state = states.NE;
						tokenBuf.append(read);
						flushTokenBuf(tokenBuf);
					} else {
						flushTokenBuf(tokenBuf);
						tokenBuf.append(read);
					}
					break;
				}

				if (read == '(') {
					state = states.BR_OPEN;
					flushTokenBuf(tokenBuf);
					tokenBuf.append(read);
					break;
				}

				if (read == '*') {
					state = states.MUL;
					if (prevState == states.BR_OPEN) {
						state = states.COMM_OPEN;
						tokenBuf.append(read);
						flushTokenBuf(tokenBuf);
					} else {
						flushTokenBuf(tokenBuf);
						tokenBuf.append(read);
					}
					break;
				}

				if (read == ')') {
					state = states.BR_CLOSE;
					if (prevState == states.MUL) {
						state = states.COMM_CLOSE;
						tokenBuf.append(read);
						flushTokenBuf(tokenBuf);
					} else {
						flushTokenBuf(tokenBuf);
						tokens.add(String.valueOf(read));
					}
					break;
				}

				if (read == '/') {
					state = states.DIV;
					if (prevState == states.DIV) {
						tokenBuf.append(read);
						flushTokenBuf(tokenBuf);
					} else {
						flushTokenBuf(tokenBuf);
						tokenBuf.append(read);
					}
					break;
				}

			} while (false);

			readByte = reader.read();
			prevState = state;
		}

	}

	private void flushTokenBuf(StringBuilder tokenBuf) {
		if (tokenBuf.length() > 0) {
			tokens.add(tokenBuf.toString());
			tokenBuf.setLength(0);
		}
	}

	private enum states {
		WORD, ASSIGN, COLON,
		EQ, NE, LT, LE, GT, GE, MUL, DIV,
		BR_OPEN, BR_CLOSE,
		COMM_OPEN, COMM_CLOSE,
		OTHER
	}
}

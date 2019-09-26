package ExpressionsAST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

class Tokenizer {
	private final BufferedReader reader;
	private final List<String> tokens;

	Tokenizer(InputStream input) {
		InputStreamReader inputReader = new InputStreamReader(input);
		this.reader = new BufferedReader(inputReader);
		this.tokens = new LinkedList<>();
	}

	void tokenize() throws IOException {
		int readByte = reader.read();
		char read;
		StringBuilder tokenBuf = new StringBuilder();
		States state = States.NUM, prevState = States.NUM;

		while (readByte != -1) {
			read = (char) readByte;
			//noinspection ConstantConditions
			do {
				if (read == ' ' || read == '\n') {
					flushTokenBuf(tokenBuf);
					break;
				}

				if (Character.isDigit(read)) {
					state = States.NUM;

					if (prevState != state) {
						flushTokenBuf(tokenBuf);
					}

					tokenBuf.append(read);
					break;
				}

				if ("+-*=<>()".contains(String.valueOf(read))) {
					state = States.OPERATOR;
					flushTokenBuf(tokenBuf);
					tokenBuf.append(read);
					break;
				}
			} while (false);

			readByte = reader.read();
			prevState = state;
		}

		flushTokenBuf(tokenBuf);
	}

	List<String> getTokens() {
		return tokens;
	}

	private void flushTokenBuf(StringBuilder buffer) {
		if (buffer.length() > 0) {
			tokens.add(buffer.toString());
			buffer.setLength(0);
		}
	}

	private enum States {
		NUM, OPERATOR
	}
}

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tokenizer {
	private BufferedReader reader;

	public Tokenizer(InputStream stream) {
		InputStreamReader inputReader = new InputStreamReader(stream);
		this.reader = new BufferedReader(inputReader);
	}

	public void tokenize() {

	}

	private enum states {
		WORD, ASSIGN,
		EQ, NE, LT, LE, GT, GE,
		ADD, SUB, MUL, DIV,
		COLON, SEMICOLON, COMMA,
		BR_OPEN, BR_CLOSE
	}
}

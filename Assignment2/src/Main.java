import ExpressionsAST.ASTBuilder;
import ExpressionsAST.Expression;
import ExpressionsAST.InvalidExpressionException;
import SimpleTokenizer.Tokenizer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

class Main {
	public static void main(String[] args) throws IOException, InvalidExpressionException {
		String expr = "1 + (26 - 98) * 15 + 777";
		ByteArrayInputStream fromString = new ByteArrayInputStream(expr.getBytes());
		Tokenizer tokenizer = new Tokenizer(fromString);
		tokenizer.tokenize();
		fromString.close();
		List<String> tokens = tokenizer.getTokens();
		for (String tok : tokens) {
			System.out.printf("\'%s\' ", tok);
		}
		System.out.println();

		ASTBuilder builder = new ASTBuilder(expr);
		Queue<String> q = builder.getEvalQueue();
		for (String s : q) {
			System.out.printf("%s ", s);
		}
		System.out.println();

		Expression root = builder.build();
		System.out.println(root.eval());
	}
}

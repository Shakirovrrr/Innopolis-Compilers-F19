import ExpressionsAST.Examples;
import ExpressionsAST.Expression;
import SimpleTokenizer.Tokenizer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
	public static void main(String[] args) throws IOException {
		Expression example = Examples.threeX2plus2();
		System.out.println(example.eval());

		String expr = "1 + (26 - 98) / 15 + 777 < 28";
		ByteArrayInputStream fromString = new ByteArrayInputStream(expr.getBytes());
		Tokenizer tokenizer = new Tokenizer(fromString);
		tokenizer.tokenize();
		List<String> tokens = tokenizer.getTokens();
		for (String tok : tokens) {
			System.out.printf("\'%s\' ", tok);
		}
		System.out.println();

		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		q.add(2);
		q.add(3);
		System.out.println(q.poll());
		System.out.println(q.peek());
	}
}

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class LexicalAnalysis {

    private ArrayList<Token> delimiters;
    private ArrayList<Token> identifiers;
    private ArrayList<Token> literals;
    private ArrayList<Token> keywords;
    private ArrayList<Token> operators;
    private ArrayList<Token> nonSupportedTokens;

    private HashMap<Integer, String> delimitersList = new HashMap<>() {
        {
            put(1, "{");
        }
    };
    private HashMap<Integer, String> operatorsList = new HashMap<>() {
        {
            put(1, "+");
            put(2, "-");
            put(3, "*");
            put(4, "/");
            put(5, ":=");
            put(6, "div");
            put(7, "mod");
            put(8, "and");
            put(9, "or");
            put(10, "not");
            put(11, "xor");
            put(12, "<>"); //not equal
            put(13, "<");
            put(14, ">");
            put(15, "=");
            put(16, "<=");
            put(17, "=<");
            put(18, ">=");
            put(20, "=>");
            put(21, "in");
        }
    };
    private HashMap<Integer, String> keywordsList = new HashMap<>() {
        {
            put(1, "begin");
            put(2, "boolean");
            put(3, "break");
            put(4, "byte");
            put(5, "do");
            put(6, "double");
            put(7, "if");
            put(8, "else");
            put(9, "end");
            put(10, "false");
            put(11, "true");
            put(12, "integer");
            put(13, "longint");
            put(17, "shortint");
            put(14, "repeat");
            put(15, "shr");
            put(16, "single");
            put(18, "then");
            put(19, "until");
            put(20, "word");
            put(21, "program");
            put(22, "while");
            put(23, "var");
            put(24, "downto");
            put(25, "label");
            put(26, "record");
            put(27, "with");
            put(28, "procedure");
            put(29, "goto");
            put(30, "packed");
            put(31, "const");
            put(32, "type");
            put(33, "case");
            put(34, "function");
            put(35, "to");
            put(36, "of");
            put(37, "for");
            put(38, "array");
            put(39, "file");
            put(40, "set");

        }
    };

    public HashMap<Integer, String> getDelimitersList() {
        return delimitersList;
    }

    public HashMap<Integer, String> getKeywordsList() {
        return keywordsList;
    }

    public HashMap<Integer, String> getOperatorsList() {
        return operatorsList;
    }

    public LexicalAnalysis() {

        delimiters = new ArrayList<>();
        identifiers = new ArrayList<>();
        literals = new ArrayList<>();
        keywords = new ArrayList<>();
        operators = new ArrayList<>();
        nonSupportedTokens = new ArrayList<>();
    }

    @Nullable
    @Contract(pure = true)
    static ArrayList<Token> parser() {
        return null;
    }

    public static void main(String[] args) throws IOException {
        //example  of tokenizator usage
		/*String sampleCode = "if (alpha>beta) then";
		InputStream fromString = new ByteArrayInputStream(sampleCode.getBytes());
		InputStream fromFile = new FileInputStream("SamplePascal.pas");
		Tokenizer tokenizer = new Tokenizer(fromFile);

		tokenizer.tokenize();
		fromFile.close();

		List<RawToken> tokens = tokenizer.getTokens();

		System.out.println("Lexical analysis START\n");
		LexicalAnalysis la = new LexicalAnalysis();
		for (String tok : tokens) {
			//System.out.println(tok);
			Token token = new Token(tok,1,1);
			la.classify(token);
		for (RawToken tok : tokens) {
			System.out.printf("[%d, %d] %s\n", tok.line, tok.place, tok.val);
		}
		*/
        System.out.println("Input the name of .pas file (like 'Name.pas') or press Enter to watch a sample file: ");
        Scanner in = new Scanner(System.in);
        String inputFile = in.nextLine();
        LexicalAnalysis la = new LexicalAnalysis();
        if (inputFile.isEmpty()) {
            inputFile = "SamplePascal.pas";
        }
        la.performLexicalAnalysis(inputFile, "Output.txt");

    }


    private void classify(@NotNull Token given) {

        if (given.isDelimiter()) {
            delimiters.add(given);

        } else if (given.isOperator()) {
            operators.add(given);
        } else if (given.isKeyword()) {
            keywords.add(given);

        } else if (given.isLiteral()) {
            literals.add(given);

        } else if (given.isIdentifier()) {
            identifiers.add(given);

        } else {
            nonSupportedTokens.add(given);
        }
    }

    public void performLexicalAnalysis(String inputPath, String outputPath) throws IOException {
        //Open file
        InputStream fromFile = new FileInputStream(inputPath);
        Tokenizer tokenizer = new Tokenizer(fromFile);

        tokenizer.tokenize();
        fromFile.close();

        List<RawToken> tokens = tokenizer.getTokens();

        FileWriter fileWriter = new FileWriter(outputPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("\n\'" + inputPath + "\'" + " to be analysed\n");
        printWriter.println("Lexical analysis START\n");
        System.out.println("\n\'" + inputPath + "\'" + " to be analysed\n");
        System.out.println("Lexical analysis START");
        printWriter.println("\nToken | line | place at line\n\n");
        LexicalAnalysis la = new LexicalAnalysis();
        for (RawToken tok : tokens) {
            Token token = new Token(tok.val, tok.line, tok.place);
            la.classify(token);
        }
        printWriter.println("Delimiters tokens: \n");
        for (Token t : la.delimiters) {
            printWriter.println(t.name + " " + t.type + " " + t.line + " " + t.place_at_line);
        }
        printWriter.println();
        printWriter.println("Literals tokens: \n");
        for (Token t : la.literals) {
            printWriter.println(t.name + " " + t.type + " " + t.line + " " + t.place_at_line);
        }
        printWriter.println();
        printWriter.println("Operators tokens: \n");
        for (Token t : la.operators) {
            printWriter.println(t.name + " " + t.type + " " + t.line + " " + t.place_at_line);
        }
        printWriter.println();
        printWriter.println("Keywords tokens: \n");
        for (Token t : la.keywords) {
            printWriter.println(t.name + " " + t.type + " " + t.line + " " + t.place_at_line);
        }
        printWriter.println();
        printWriter.println("Identifiers tokens: \n");
        for (Token t : la.identifiers) {
            printWriter.println(t.name + " " + t.type + " " + t.line + " " + t.place_at_line);
        }
        printWriter.println();
        printWriter.println("Not defined tokens: \n");
        for (Token t : la.nonSupportedTokens) {
            printWriter.println(t.name + " " + t.type + " " + t.line + " " + t.place_at_line);
        }

        printWriter.println("\nLexical analysis DONE");
        System.out.println("Lexical analysis DONE \n\nlook for the results in \'" + outputPath + "\'");
        printWriter.close();

        //открыть файл
        // спарсить токены
        // по списку токенов пройти и проклассифицировать
        // запринтить аутпут
        // вы восхитительны
    }

}
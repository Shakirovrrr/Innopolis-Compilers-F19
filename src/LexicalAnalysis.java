import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;


public class LexicalAnalysis {

    private ArrayList<Token> delimiters;
    private ArrayList<Token> identifiers;
    private ArrayList<Token> literals;
    private ArrayList<Token> keywords;
    private ArrayList<Token> operators;


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

        }
    }

    @Nullable
    @Contract(pure = true)
    static ArrayList<Token> parser() {
        return null;
    }

    public void performLexicalAnalysis(String path) {
        //открыть файл
        // спарсить токены
        // по списку токенов пройти и проклассифицировать
        // запринтить аутпут

    }


    public static void main(String[] args) {
        System.out.println("The skeleton is about to be ready");

    }
}
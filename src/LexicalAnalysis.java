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

    private HashMap<Integer, Character> delimitersList = new HashMap<>() {
        {
            put(1, '/');
        }
    };
    private HashMap<Integer, String> operatorsList = new HashMap<>() {
        {
            put(1, "+");
        }
    };
    private HashMap<Integer, String> keywordsList = new HashMap<>() {
        {
            put(1, "begin");
        }
    };


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

        } else if (given.isIdentifier()) {
            identifiers.add(given);

        } else if (given.isLiteral()) {
            literals.add(given);

        } else if (given.isKeyword()) {
            keywords.add(given);

        } else if (given.isOperator()) {
            operators.add(given);
        }
    }

    @Nullable
    @Contract(pure = true)
    static ArrayList<Token> parser() {
        return null;
    }


    public static void main(String[] args) {
        System.out.println("The skeleton is about to be ready");

    }
}
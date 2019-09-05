import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class main {

    private ArrayList <token> delimiters = null;
    private ArrayList <token> identifiers = null;
    private ArrayList <token> integers = null;
    private ArrayList <token> keywords = null;
    private ArrayList <token> operators = null;

    private void classify(@NotNull token given){
        if (given.isDelimiter()) {
            given.type = "delimiter";
            delimiters.add(given);
        }
        else if (given.isIdentifier()){
            given.type = "identifier";
            identifiers.add(given);
        }
        else if (given.isInteger()){
            given.type = "integer";
            integers.add(given);
        }
        else if (given.isKeyword()){
            given.type = "keyword";
            keywords.add(given);
        }
        else if (given.isOperator())
            given.type = "operator";
            operators.add(given);
    }

    @Nullable
    @Contract(pure = true)
    static ArrayList <token> parser(){
        return null;
    }

    public static void main(String[] args) {
        System.out.println("The skeleton is about to be ready");
    }
}
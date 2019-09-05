public class token {
    public String name;
    public String type;
    public int line;
    public int place_at_line;

    public Boolean isDelimiter (){
        return true;
    }

    public Boolean isIdentifier (){
        return true;
    }

    public Boolean isInteger (){
        return true;
    }

    public Boolean isOperator (){
        return true;
    }

    public Boolean isKeyword (){
        return true;
    }
}



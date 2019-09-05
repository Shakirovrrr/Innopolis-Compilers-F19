public class Token {
    public String name;
    public TokenType type;
    public int line;
    public int place_at_line;

    private enum TokenType{
        DELIMETER,
        IDENTIFIER,
        LITERAL,
        KEYWORD,
        OPERATOR,
        NOT_A_TYPE,

    }
    Token(String name, int line, int place_at_line) {
        this.line = line;
        this.name = name;
        this.place_at_line = place_at_line;
        this.type = TokenType.NOT_A_TYPE;
    }

    private void setTokenType(TokenType tokenType ){
        this.type = tokenType;
    }

    public Boolean isDelimiter() {
        setTokenType(TokenType.DELIMETER);
        return true;
    }

    public Boolean isIdentifier() {
        setTokenType(TokenType.IDENTIFIER);
        return true;
    }

    public Boolean isLiteral() {
        setTokenType(TokenType.LITERAL);
        return true;
    }

    public Boolean isOperator() {
        setTokenType(TokenType.OPERATOR);
        return true;
    }

    public Boolean isKeyword() {
        setTokenType(TokenType.KEYWORD);
        return true;
    }
}



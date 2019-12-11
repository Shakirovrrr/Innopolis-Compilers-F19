public class Token {
    public String name;
    public TokenType type;
    public int line;
    public int place_at_line;

    private LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();

    private enum TokenType {
        DELIMITER,
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

    private void setTokenType(TokenType tokenType) {
        this.type = tokenType;
    }

    public Boolean isDelimiter() {
        if (this.lexicalAnalysis.getDelimitersList().containsValue(this.name)) {
            setTokenType(TokenType.DELIMITER);
            return true;
        }
        return false;
    }

    private Boolean consistLegal() {
        char[] temp = this.name.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (!((int) temp[i] == (int) '_' ||
                    (65 <= (int) temp[i]) && ((int) temp[i] <= 90) ||
                    (97 <= (int) temp[i]) && (int) temp[i] <= 122)) {
                return false;
            } else {
                if (this.lexicalAnalysis.getOperatorsList().containsValue(String.valueOf(temp[i])) ||
                        this.lexicalAnalysis.getDelimitersList().containsValue(String.valueOf(temp[i]))) {
                    return false;
                }
            }
        }

        return true;
    }

    public Boolean isIdentifier() {
        if (this.consistLegal()
        ) {
            setTokenType(TokenType.IDENTIFIER);
            return true;
        } else return false;
    }

    public Boolean isLiteral() {
        try {
            Integer.parseInt(this.name);
            setTokenType(TokenType.LITERAL);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public Boolean isOperator() {
        if (this.lexicalAnalysis.getOperatorsList().containsValue(this.name)) {
            setTokenType(TokenType.OPERATOR);
            return true;
        }
        return false;

    }

    public Boolean isKeyword() {
        if (this.lexicalAnalysis.getKeywordsList().containsValue(this.name)) {
            setTokenType(TokenType.KEYWORD);
            return true;
        }
        return false;
    }


}



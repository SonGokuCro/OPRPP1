package hr.fer.oprpp1.hw02.prob1;

public class Token {
    TokenType type;
    Object value;
    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Object getValue() {

        return null;
    }

    public TokenType getType() {

        return TokenType.EOF;
    }
}

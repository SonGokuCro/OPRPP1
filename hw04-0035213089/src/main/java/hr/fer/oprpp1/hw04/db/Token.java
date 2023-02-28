package hr.fer.oprpp1.hw04.db;

/**
 * Class representing <code>token</code>
 * needed to write lexer.
 */
public class Token {
    TokenType type;
    String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }
}


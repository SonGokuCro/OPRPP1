package hr.fer.oprpp1.hw04.db;

/**
 * Lexer class used for processing tokens from user inputted query.
 */
public class QueryLexer {
    private char[] sequence;
    private int currentIndex;
    private Token token;

    public QueryLexer(String text) {
        sequence = text.toCharArray();
        currentIndex = 0;
    }

    public Token getToken() {
        return token;
    }

    /**
     * Getter for next token from given query.
     * @return token generated from input query
     * @throws LexerException invalid character in query
     */
    public Token nextToken() throws LexerException {
        boolean passed = false;

        while (currentIndex != sequence.length) {
            if (Character.isWhitespace(sequence[currentIndex])) {
                currentIndex++;
            } else if(Character.isLetter(sequence[currentIndex])) {
                token = wordToken();
                passed = true;
                break;
            } else if(isNotEquals()) {
                StringBuilder sb = new StringBuilder();
                sb.append(sequence[currentIndex++]);
                if(sequence[currentIndex] == '=') {
                    sb.append(sequence[currentIndex++]);
                }
                token = new Token(TokenType.OPERATOR, sb.toString());
                passed = true;
                break;
            } else if (sequence[currentIndex] == '=') {
                token = new Token(TokenType.OPERATOR, "=");
                passed = true;
                currentIndex++;
                break;
            } else if(sequence[currentIndex] == '\"') {
                token = literalToken();
                passed = true;
                break;
            } else {
                throw new LexerException("Invalid character appeared in query");
            }
        }
        if (currentIndex >= sequence.length && !passed){
            token = new Token(TokenType.EOF, null);
        }
        return token;
    }

    /**
     * Helper method used to check if token is literal
     * @return token if tokenizing is successful
     * @throws LexerException if query is invalid
     */
    private Token literalToken() throws LexerException {
        StringBuilder sb = new StringBuilder();
        currentIndex++;

        while (currentIndex != sequence.length && sequence[currentIndex] != '\"') {
            sb.append(sequence[currentIndex++]);
        }
        if (currentIndex == sequence.length) {
            throw new LexerException("String literals not closed properly.");
        }
        currentIndex++;
        return new Token(TokenType.LITERAL, sb.toString());
    }

    /**
     * Helper method to check if next character is one of the following
     * to determine the needed operator.
     * @return <code>true</code> if character matches, else <code>false</code>
     */
    private boolean isNotEquals() {
        return sequence[currentIndex] == '>'
                || sequence[currentIndex] == '!'
                || sequence[currentIndex] == '<';
    }

    /**
     * Helper method used to determine the token type.
     * @return token if tokenizing is successful
     */
    private Token wordToken() {
        StringBuilder sb = new StringBuilder();
        while(currentIndex != sequence.length && (Character.isLetter(sequence[currentIndex]) || sequence[currentIndex] == '*')) {
            sb.append(sequence[currentIndex++]);
        }
        if(sb.toString().equalsIgnoreCase("AND")) {
            return new Token(TokenType.AND, "and");
        } else if (sb.toString().equals("LIKE")) {
            return new Token(TokenType.OPERATOR, "LIKE");
        }
        return new Token(TokenType.ATTRIBUTE, sb.toString());
    }

}

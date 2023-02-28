package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser for query language used to obtain information from database.
 */
public class QueryParser {
    private List<ConditionalExpression> query;
    private QueryLexer lexer;
    private boolean isDirectQuery = false;

    /**
     * Query constructor
     * @param text query written by user of this program
     */
    public QueryParser(String text){
        if (text == null) {
            throw new NullPointerException("Empty query!");
        }
        query = new ArrayList<>();
        lexer = new QueryLexer(text);
        try {
            tokenize();
        } catch (LexerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method in charge of making tokens out of query.
     * @throws LexerException if query is invalid
     */
    private void tokenize() throws LexerException {
        while(lexer.nextToken().getType() != TokenType.EOF) {
            if (lexer.getToken().getType() == TokenType.ATTRIBUTE)
                query.add(createCondition());
            else if (lexer.getToken().getType() == TokenType.AND && query.size() != 0)
                isDirectQuery = false;
            else
                throw new IllegalStateException("Not properly written query.");
        }
    }

    /**
     * Method used to ensure that query is in valid format.
     * @return ConditionalExpression if query is structured with set rules.
     * @throws LexerException if query is not valid
     */
    private ConditionalExpression createCondition() throws LexerException {
        String attributeString = lexer.getToken().getValue();
        IFieldValueGetter attribute = findAttribute(attributeString);
        if (lexer.nextToken().getType() != TokenType.OPERATOR) {
            throw new IllegalArgumentException("After attribute name must come an operator.");
        }

        String operatorString = lexer.getToken().getValue();
        if (lexer.nextToken().getType() != TokenType.LITERAL) {
            throw new IllegalArgumentException("After operator must come a literal.");
        }

        IComparisonOperator operator = findOperator(operatorString);
        String literal = lexer.getToken().getValue();
        return new ConditionalExpression(attribute, literal, operator);
    }

    /**
     * Method used for obtaining information on wanted attribute in query.
     * @param attribute that we want to fetch
     * @return FieldValueGetters => returning student attribute
     * @throws IllegalArgumentException if attribute used is not: jmbag, firstName, lastName
     */
    private IFieldValueGetter findAttribute(String attribute) {
        switch (attribute) {
            case ("firstName") -> { return FieldValueGetters.FIRST_NAME; }
            case ("lastName") -> { return FieldValueGetters.LAST_NAME; }
            case ("jmbag") -> {
                if (query.size() == 0)
                    isDirectQuery = true;
                return FieldValueGetters.JMBAG;
            }
            default -> throw new IllegalArgumentException("Attribute not found!");
        }
    }

    /**
     * Determing the type of operator.
     * @param operator operator
     * @return ComparisonOperators depending on string sequence
     */
    private IComparisonOperator findOperator(String operator) {
        return switch (operator) {
            case ("<") -> ComparisonOperators.LESS;
            case ("<=") -> ComparisonOperators.LESS_OR_EQUALS;
            case (">") -> ComparisonOperators.GREATER;
            case (">=") -> ComparisonOperators.GREATER_OR_EQUALS;
            case ("=") -> ComparisonOperators.EQUALS;
            case ("!=") -> ComparisonOperators.NOT_EQUALS;
            default -> ComparisonOperators.LIKE;
        };
    }

    /**
     * @return <code>true</code> if query only condition is jmbag,
     *         <code>false</code> otherwise
     */
    public boolean isDirectQuery() {
        return isDirectQuery;
    }

    /**
     * Method used for fetching jmbag of direct quary
     * @return jmbag
     * @throws IllegalArgumentException if query is not direct
     */
    String getQueriedJMBAG() {
        if (!isDirectQuery){
            throw new IllegalStateException("Not a direct query!");
        }
        return query.get(0).getLiteral();
    }

    /**
     * Method for fetching a list of conditional expressions from query
     * @return query
     */
    public List<ConditionalExpression> getQuery() {
        return query;
    }

}

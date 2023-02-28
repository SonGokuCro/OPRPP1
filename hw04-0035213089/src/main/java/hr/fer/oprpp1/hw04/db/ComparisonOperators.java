package hr.fer.oprpp1.hw04.db;

/**
 * Strategies implementation for each comparison operator that we
 * will implement in our query language.
 */
public class ComparisonOperators {
    public static final IComparisonOperator LESS = (s1, s2) -> s1.compareTo(s2) < 0;
    public static final IComparisonOperator LESS_OR_EQUALS = (s1, s2) -> s1.compareTo(s2) <= 0;
    public static final IComparisonOperator GREATER = (s1, s2) -> s1.compareTo(s2) > 0;
    public static final IComparisonOperator GREATER_OR_EQUALS = (s1, s2) -> s1.compareTo(s2) >= 0;
    public static final IComparisonOperator EQUALS = String::equals;
    public static final IComparisonOperator NOT_EQUALS = String::equals;
    public static final IComparisonOperator LIKE = (s1, s2) -> {
        if(s2.indexOf('*') != s2.lastIndexOf('*')) {
            throw new IllegalArgumentException("Can not have more than one * in pattern");
        }
        if(s2.startsWith("*")) {
            return s1.endsWith(s2.substring(1));
        } else if(s2.endsWith("*")) {
            return s1.startsWith(s2.substring(0, s2.length()-1));
        }
        int index = s2.indexOf('*');
        return (s1.substring(0,index).equals(s2.substring(0,index))
                    && s1.substring(index).endsWith(s2.substring(index + 1)));
    };
}

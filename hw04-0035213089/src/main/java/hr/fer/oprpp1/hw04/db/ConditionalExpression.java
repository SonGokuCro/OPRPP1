package hr.fer.oprpp1.hw04.db;


/**
 * Class made for emulating conditional expression.
 */
public class ConditionalExpression {
    private IFieldValueGetter fvg;
    private String literal;
    private IComparisonOperator operator;

    public ConditionalExpression(IFieldValueGetter fvg, String str, IComparisonOperator co) {
        this.fvg = fvg;
        this.literal = str;
        this.operator = co;
    }

    public IFieldValueGetter getFvg() { return fvg; }

    public String getLiteral() {
        return literal;
    }

    public IComparisonOperator getOperator() {
        return operator;
    }

}

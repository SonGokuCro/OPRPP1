package hr.fer.oprpp1.hw04.db;

import java.util.List;

/**
 * Class used for filtering student database entries using
 * values representing the student.
 */
public class QueryFilter implements IFilter{

    List<ConditionalExpression> list;

    public QueryFilter(List<ConditionalExpression> list) {
        this.list = list;
    }

    /**
     * Method used to filter record using certain conditions.
     * @param record entry representing student data
     * @return <code>boolean</code> if entry satisfies condition, <code>false</code> otherwise
     */
    @Override
    public boolean accepts(StudentRecord record) {
        int count = 0;
        boolean isAccepted;
        for(ConditionalExpression ce : list) {
            isAccepted = ce.getOperator().satisfied(ce.getFvg().get(record), ce.getLiteral());
            if(isAccepted) {
                count++;
            }
        }
        return count == list.size();
    }
}

package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComparisonOperatorsTest {
    @Test
    void testLess() {
        IComparisonOperator less = ComparisonOperators.LESS;
        assertEquals(true, less.satisfied("3", "4"));
        assertEquals(false, less.satisfied("4", "3"));
    }

    @Test
    void testLessOrEquals() {
        IComparisonOperator lessOrEquals = ComparisonOperators.LESS_OR_EQUALS;
        assertEquals(true, lessOrEquals.satisfied("3", "4"));
        assertEquals(true, lessOrEquals.satisfied("4", "4"));
        assertEquals(false, lessOrEquals.satisfied("4", "3"));
    }

    @Test
    void testGreater() {
        IComparisonOperator greater = ComparisonOperators.GREATER;
        assertEquals(true, greater.satisfied("4", "3"));
        assertEquals(false, greater.satisfied("3", "4"));
    }

    @Test
    void testGreaterOrEquals() {
        IComparisonOperator greaterOrEquals = ComparisonOperators.GREATER_OR_EQUALS;
        assertEquals(true, greaterOrEquals.satisfied("4", "3"));
        assertEquals(false, greaterOrEquals.satisfied("3", "4"));
        assertEquals(true, greaterOrEquals.satisfied("4", "4"));
    }

    @Test
    void testEquals() {
        IComparisonOperator equals = ComparisonOperators.EQUALS;
        assertEquals(true, equals.satisfied("3", "3"));
    }

    @Test
    void testNotEquals() {
        IComparisonOperator notEquals = ComparisonOperators.NOT_EQUALS;
        assertNotEquals(true, notEquals.satisfied("31", "4"));
    }

    @Test
    void testLike() {
        IComparisonOperator like = ComparisonOperators.LIKE;
        assertEquals(true, like.satisfied("AAAA", "AA*AA"));
        assertEquals(false, like.satisfied("AAA", "AA*AA"));
    }
}
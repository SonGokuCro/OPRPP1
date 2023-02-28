package hr.fer.oprpp1.hw04.db;

/**
 * Interface used to ensure that class need to implement accepts method.
 */
public interface IFilter {
    boolean accepts(StudentRecord record);
}

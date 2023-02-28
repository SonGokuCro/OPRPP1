package hr.fer.oprpp1.hw04.db;

/**
 * Used to implement strategy of obtaining requested field.
 */
public interface IFieldValueGetter {

    String get(StudentRecord record);

}

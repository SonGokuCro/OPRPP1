package hr.fer.oprpp1.hw04.db;


/**
 * Class used for obtaining requested field value from given record.
 */
public class FieldValueGetters{

    /**
     * Method used for obtaining <code>first name</code> field value from given record.
     */
    public static final IFieldValueGetter FIRST_NAME = (record) -> record.getFirstName();
    /**
     * Method used for obtaining <code>last name</code> field value from given record.
     */
    public static final IFieldValueGetter LAST_NAME = (record) -> record.getLastName();
    /**
     * Method used for obtaining <code>jmbag</code> field value from given record.
     */
    public static final IFieldValueGetter JMBAG = (record) -> record.getJmbag();

}

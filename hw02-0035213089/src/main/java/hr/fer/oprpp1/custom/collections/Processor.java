package hr.fer.oprpp1.custom.collections;

/**
 * This class is model of an object capable of performing
 * some operations on passed object.
 *
 * @author Slađan Tadić
 * @version 1.0
 */
public interface Processor {
    /**
     * This method is used to process object depending on the intended use.
     *
     * @param value value to be processed
     */
    void process(Object value);
}

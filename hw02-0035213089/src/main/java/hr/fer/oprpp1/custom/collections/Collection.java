package hr.fer.oprpp1.custom.collections;

import hr.fer.oprpp1.custom.collections.demo.EvenIntegerTester;

/**
 * This class is represents general collection of object.
 *
 * @author Slađan Tadić
 * @version 1.0
 */
public interface Collection {

    /**
     * This method is checking if the collection is empty or not.
     *
     * @return <code>true</code> if size is 0,
     * else <code>false</code>.
     * @since 1.0
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * This method tells us the size of collection.
     *
     * @return 0
     * @since 1.0
     */
    int size();

    /**
     * This method adds Object type param to the collection.
     *
     * @param value value that needs to be added to the collection.
     * @since 1.0
     */
    void add(Object value);

    /**
     * This method is used to determine whether is Object contained in collection.
     *
     * @param value value that needs to be checked if it is in collection.
     * @return <code>false</code>
     * @since 1.0
     */
    boolean contains(Object value);

    /**
     * This method is used to remove Object from the collection.
     *
     * @param value value that needs to be removed from the collection
     * @return <code>false</code>
     */
    boolean remove(Object value);

    /**
     * This method is used to make an array out of collection.
     *
     * @return Object[]
     * @since 1.0
     */
    Object[] toArray();

//    /**
//     * The use of this method is decided upon implementation in extending classes.
//     * Can be anything.
//     *
//     * @param processor processor is determined upon implementation
//     * @since 1.0
//     */
//    void forEach(Processor processor);

    /**
     * Method adds into the current collection all elements from the given collection.
     * Other collection remains unchanged.
     *
     * @param other other collection used to add elements to current collection
     * @since 1.0
     */
    default void addAll(Collection other) {
        class Process implements Processor {
            @Override
            public void process(Object value) {
                add(value);
            }
        }
        Process p = new Process();
        other.forEach(p);
    }

    /**
     * Removes all elements from this collection.
     *
     * @since 1.0
     */
    void clear();

    ElementsGetter createElementsGetter();

    /**
     * method used to add all elements in collection that satisfy certain condition
     * @param col collection from which we test elements
     * @param tester is helper class with test method
     */
    default void addAllSatisfying(Collection col, Tester tester){
        ElementsGetter getter = col.createElementsGetter();
        while (getter.hasNextElement()) {
            Object obj = getter.getNextElement();
            if(tester.test(obj)) {
                this.add(obj);
            }
        }
    }

    /**
     * Method to apply certain action on each elements in the collection
     * @param processor is implemented upon use
     */
    default void forEach(Processor processor) {
        ElementsGetter eg = this.createElementsGetter();
        eg.processRemaining(processor);
    }
}
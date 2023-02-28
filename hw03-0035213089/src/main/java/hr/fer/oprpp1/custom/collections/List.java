package hr.fer.oprpp1.custom.collections;

public interface List<E> extends Collection<E> {
    /**
     * Method to get object on index
     * @param index on which we want to get object
     * @return Object if successful
     */
    default E get(int index) {
        return this.get(index);
    }

    /**
     * Inserts object on given position
     * @param value of object which we want to store in the collection
     * @param position on which we want to store object in the collection
     */
    default void insert(E value, int position) {
        this.insert(value, position);
    }

    /**
     * Method used to determine place of the object in the collection
     * @param value of object that we want to get the index of
     * @return int index of the given object in collection
     */
    default int indexOf(Object value) {
        return this.indexOf(value);
    }

    /**
     * Used to remove object at given index
     * @param index place in the collection
     */
    default void remove(int index) {
        this.remove(index);
    }


}

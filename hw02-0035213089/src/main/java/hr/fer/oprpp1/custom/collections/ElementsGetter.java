package hr.fer.oprpp1.custom.collections;

public interface ElementsGetter {
    /**
     * method used to determine if we have any more elements in collection
     * @return <code>true</code> if there exists next element, <code>false</code> otherwise
     */
    boolean hasNextElement();

    /**
     * method used to get next elements in collection
     * @return Object if he manages to get the element
     */
    Object getNextElement();

    /**
     * Used to process remaining elements in collection
     * @param processor is implemented when called
     */
    default void processRemaining(Processor processor) {
        while(this.hasNextElement()) {
            processor.process(this.getNextElement());
        }
    }
}

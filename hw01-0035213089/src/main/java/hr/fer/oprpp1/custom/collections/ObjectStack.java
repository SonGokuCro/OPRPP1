package hr.fer.oprpp1.custom.collections;

/**
 * This class is used to implement the stack-like collection.
 *
 * @author Slađan Tadić
 * @version 1.0
 */
public class ObjectStack {

    /**
     * ArrayIndexedCollection member is used to emulate stack-like structure;
     */
    private ArrayIndexedCollection col;

    /**
     * Constructor for class ObjectStack
     * @since 1.0
     */
    public ObjectStack() {
        this.col = new ArrayIndexedCollection();
    }

    /**
     * This method uses method isEmpty() provided by class Collection to implement functionality
     * @return <code>true</code> if stack is empty, otherwise <code>false</code>
     * @since 1.0
     */
    public boolean isEmpty() {
        return col.isEmpty();
    }

    /**
     * This method uses method size() provided by class ArrayIndexedCollection to implement functionality
     * @return size of stack-like implementation
     * @since 1.0
     */
    public int size() {
        return col.size();
    }

    /**
     * This method uses method add() provided by class ArrayIndexedCollection to implement functionality
     * to "put" object on top of the stack-like structure.
     * @since 1.0
     */
    public void push(Object value) {
        this.col.add(value);
    }

    /**
     * This method uses method get() provided by class ArrayIndexedCollection to implement functionality
     * to "pop" object on top of the stack-like structure.
     * @return object object on top of the stack-like structure
     * @throws EmptyStackException is stack is empty
     * @since 1.0
     */
    public Object pop() {
        if(col.isEmpty()) {
            throw new EmptyStackException("The stack is already empty!");
        } else {
            Object top = col.get(col.size() - 1);
            col.remove(col.size() - 1);
            return top;
        }
    }

    /**
     * This method uses method get() provided by class ArrayIndexedCollection to implement functionality
     * to "peek" at the object on top of the stack-like structure.
     * @return object object on top of the stack-like structure
     * @throws EmptyStackException is stack is empty
     * @since 1.0
     */
    public Object peek() {
        if(col.isEmpty()) {
            throw new EmptyStackException("The stack is empty!");
        } else {
            return col.get(col.size() - 1);
        }
    }

    /**
     * This method uses clear() provided by class ArrayIndexedCollection to clear the stack-like structure.
     * @since 1.0
     */
    public void clear() {
        col.clear();
    }

    public void print() { col.print();}
}

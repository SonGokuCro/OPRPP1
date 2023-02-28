package hr.fer.oprpp1.custom.collections;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * This class is used to implement resizable array-backed collection of objects.
 *
 * The most frequent use of this class is for storing objects in array.
 * @author Slađan Tadić
 * @version 1.0
 */
public class ArrayIndexedCollection<E> implements List<E>{

    /**
     * size is size of the collection which doesn't count null references
     * elements is array which we use to store collection
     */
    private int size;
    private E[] elements;
    private long modificationCount = 0;

    /**
     * Constructor for class ArrayIndexedCollection
     *
     * @param other other collection from which elements
     *              are copied in this new collection
     * @param initialCapacity initial capacity of collection
     * @throws NullPointerException if other is null
     */
    public ArrayIndexedCollection(Collection<? extends E> other, int initialCapacity) {
        if (other == null) {
            throw new NullPointerException("msg");
        } else {
            if(initialCapacity < other.size()) {
                this.size = other.size();
                this.elements = (E[]) Arrays.copyOf(other.toArray(), other.size());
                this.modificationCount++;
            } else {
                this.size = initialCapacity;
                this.elements = (E[]) Arrays.copyOf(other.toArray(), initialCapacity);
            }
        }
    }

    /**
     * Another constructor for class ArrayIndexedCollection
     *
     * @param initialCapacity initial capacity of collection
     * @throws IllegalArgumentException if initial capacity is < 1
     */
    public ArrayIndexedCollection(int initialCapacity) {
        this.size = 0;
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("msg");
        } else {
            this.elements = (E[]) new Object[initialCapacity];
        }
    }

    /**
     * Another constructor for ArrayIndexedCollection
     *
     * @param other other collection from which elements
     *              are copied in this new collection
     */
    public ArrayIndexedCollection(Collection<? extends E> other) {
        this(other, other.size());
    }

    /**
     * Another constructor for ArrayIndexedCollection
     * initialCapacity if not used as parameter is 16.
     */
    public ArrayIndexedCollection() {
        this(16);
    }

    /**
     * This method is used to determine the size of collection.
     *
     * Example of use:
     * <pre>
     *     ArrayIndexedCollection col = elements[] = {3, 4, 1, 9, 6};
     *     // do something
     *     int numOfElements = col.size();
     * </pre>
     * @return numOfElements
     * @since 1.0
     */
    @Override
    public int size() {
        int numOfElements = 0;
        for (Object element : elements) {
            if (element != null) {
                numOfElements++;
            }
            if(element == null) {
                break;
            }
        }
        return numOfElements;
    }

    /**
     * This method adds Object type param to the collection.
     *
     * * Example of use:
     *  <pre>
     *      ArrayIndexedCollection col = new ArrayIndexedCollection();
     *      // do something
     *      col.add(value);
     *  </pre>
     * @param value value that needs to be added to the collection.
     * @since 1.0
     */
    @Override
    public void add(E value) {
        if(value == null) {
            throw new NullPointerException("msg");
        }
        for(int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                elements[i] = value;
                this.size++;
                break;
            } else if (i == elements.length - 1) {
                elements = Arrays.copyOf(elements, elements.length * 2);
            }
        }
        this.modificationCount++;
    }

    /**
     *This method is used to determine whether is object contained in collection.
     *
     * Example of use:
     * <pre>
     *     ArrayIndexedCollection col = new ArrayIndexedCollection();
     *     //do something
     *     col.contains(value);
     * </pre>
     * @param value value that needs to be checked if it is in collection.
     * @return <code>false</code> if object is not in collection,
     *         else <code>true</code>
     * @since 1.0
     */
    @Override
    public boolean contains(Object value) {
        int numOfElem = this.size;
        for (int i = 0; i < numOfElem; i++) {
            if (elements[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to remove object from the collection.
     *
     * Example of use:
     * <pre>
     *     ArrayIndexedCollection col = new ArrayIndexedCollection();
     *     //do something
     *     col.remove(value);
     * </pre>
     * @param value value that needs to be removed from the collection
     * @return <code>true</code> if object is removed, else <code>false</code>
     * @since 1.0
     */
    @Override
    public boolean remove(Object value) {
        this.modificationCount++;
        Object[] temp = new Object[elements.length];
        int sizeOfElem = this.size;
        for (int i = 0; i < sizeOfElem; i++) {
            if (value.equals(elements[i])) {
                System.arraycopy(elements, 0, temp, 0, i);
                System.arraycopy(elements, i + 1, temp, i, elements.length - i - 1);
                this.size--;
                elements = (E[]) temp;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all elements from this collection.
     * Example of use:
     * <pre>
     *     ArrayIndexedCollection col = new ArrayIndexedCollection();
     *     //do something
     *     col.clear();
     * </pre>
     * @since 1.0
     */
    @Override
    public void clear() {
        int sizeOfElem = this.size;
        for (int i = 0; i < sizeOfElem; i++) {
            elements[i] = null;
        }
        this.size = 0;
    }

    /**
     * This method is used to make an array out of collection.
     * Example of use:
     * <pre>
     *     ArrayIndexedCollection col = new ArrayIndexedCollection();
     *     //do something
     *     Object[] array = col.toArray();
     * </pre>
     * @return Object[]
     * @since 1.0
     */
    @Override
    public E[] toArray() {
        return Arrays.copyOf(elements, this.size);
    }

    /**
     * This method is used to get object from collection on index.
     * Example of use:
     * <pre>
     *     ArrayIndexedCollection col = new ArrayIndexedCollection();
     *     //do something
     *     Object temp = col.get(index);
     * </pre>
     *
     * @param index index of object which we want to get from collection
     * @return object if index is valid
     * @throws IndexOutOfBoundsException if <code>index</code> is out of bounds.
     * @since 1.0
     */
    public E get(int index) {
        if(index >= 0 && index <= this.size) {
            return this.elements[index];
        } else {
            throw new IndexOutOfBoundsException("msg");
        }
    }

    /**
     * This method inserts given value at the given position in array with shifting.
     * Example of use:
     * <pre>
     *     ArrayIndexedCollection col = new ArrayIndexedCollection();
     *     //do something
     *     col.insert(value, index);
     * </pre>
     * @param value value of object that we want to insert
     * @param position position on which we want to insert the object
     * @since 1.0
     */
    public void insert(Object value, int position) {
        this.modificationCount++;
        int index = position - 1;
        if(index >= 0 && index <= this.size) {
            this.size++;
            if(this.size > elements.length) {
                elements = Arrays.copyOf(elements, 2 * this.size);
            }
            if (this.size - (index + 1) >= 0)
                System.arraycopy(elements, index + 2 - 2, elements, index + 1, this.size - (index + 1));
            elements[index] = (E) value;
        } else {
            throw new IndexOutOfBoundsException("msg");
        }
    }

    /**
     * This method searches the collection and returns the index of the first occurrence of the given value.
     * Example of use:
     * <pre>
     *      ArrayIndexedCollection col = new ArrayIndexedCollection();
     *      //Do something
     *      int indexed = col.indexOf(value);
     *  </pre>
     *
     * @param value value of object that we are searching for
     * @return <code>-1</code> if object is not found, else <code>index</code> of given value in array.
     * @since 1.0
     */
    public int indexOf(Object value) {
        if(value == null) {
            return -1;
        }

        int numOfElem = this.size;
        for(int i = 0; i < numOfElem; i++) {
            if (value.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method removes element at specified index from collection and applies
     * shifting of objects in array.
     *
     * Example of use:
     * <pre>
     *      ArrayIndexedCollection col = new ArrayIndexedCollection();
     *      //Do something
     *      col.remove(index);
     * </pre>
     * @param index on which index in array we want to remove object
     * @throws IndexOutOfBoundsException for invalid index
     */
    public void remove(int index) {
        this.modificationCount++;
        if(index >= 0 && index <= this.size) {
            elements[index] = null;
            for(int i = index + 1; i < this.size; i++) {
                elements[index++] = elements[i];
                elements[i] = null;
            }
            this.size--;
        } else {
            throw new IndexOutOfBoundsException("msg");
        }
    }

//    public void print() {
//        int numOfElements = this.size;
//        for(int i = 0; i < numOfElements; i++) {
//            if(elements[i] != null) {
//                System.out.println(this.elements[i]);
//            }
//        }
//    }

    /**
     * This method adds all elements from other collection to this collection
     *
     * Example of use:
     * <pre>
     *      ArrayIndexedCollection col = new ArrayIndexedCollection();
     *      col.addAll(other);
     * </pre>
     * @param other collection we want to add
     */
    @Override
    public void addAll(Collection<? extends E> other) {
        ArrayIndexedCollection<E> col = (ArrayIndexedCollection<E>) other;
        for(int i = 0; i < other.size(); i++) {
            this.elements[i] = col.get(i);
        }
    }

    /**
     * Class used for accessing array typed collection
     */
    private static class arrayIndexedElementsGetter<E> implements ElementsGetter<E> {

        ArrayIndexedCollection<E> list;
        long savedModificationCount;
        long count;

        /**
         * Constructor for arrayIndexedElementsGetter
         * @param other collection which we are iterating
         * @param modificationCount helps us know how many times we have modified collection
         * count is helper variable used for comparison
         */
        public arrayIndexedElementsGetter(ArrayIndexedCollection<E> other, long modificationCount) {
            this.list = other;
            this.savedModificationCount = modificationCount;
            this.count = modificationCount;
        }
        int i = 0, j = 0;
        /**
         * delivered is used to determine if we have passed the element to getNextElementMethod
         */
        boolean delivered = false;

        /**
         * method used to determine if we have any more elements in collection
         *      arrayIndexedElementsGetter eg = new arrayIndexedElementsGetter();
         *      if(eg.hasNextElement()) {
         *          //do something
         *      }
         * @return <code>true</code> if there exists next element, <code>false</code> otherwise
         */
        @Override
        public boolean hasNextElement() {
            if(this.savedModificationCount != count) {
                throw new ConcurrentModificationException("msg");
            }
            if(delivered) {
                this.i++;
                if(i >= list.size) {
                    return false;
                }
                delivered = false;
            }
            return true;
        }

        /**
         * method used to get next elements in collection
         *      arrayIndexedElementsGetter eg = new arrayIndexedElementsGetter();
         *      if(eg.hasNextElement()) {
         *          eg.getNextElement();
         *      }
         * @return Object if he manages to get the element
         * @throws NoSuchElementException if there is no more elements in collection
         */
        @Override
        public E getNextElement() {
            if(this.savedModificationCount != count) {
                throw new ConcurrentModificationException("msg");
            }
            E obj = this.list.elements[j];
            if(obj != null) {
                if(j < this.list.size) {
                    j++;
                }
                delivered = true;
                return obj;
            } else {
                throw new NoSuchElementException("msg");
            }
        }
    }

    /**
     * ElementsGetter constructor
     * @return arrayIndexedElementGetter
     */
    @Override
    public ElementsGetter<E> createElementsGetter() {
        return new arrayIndexedElementsGetter<>(this, modificationCount);
    }
}

package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * This is  implementation of linked list-backed collection of objects.
 *
 * @author Slađan Tadić
 * @version 1.0
 */
public class LinkedListIndexedCollection implements List {

    /**
     * size is size of the collection which doesn't count null references
     * first is reference to the first node in the list
     * last is reference to the last node in the list
     */
    private int size;
    private ListNode first;
    private ListNode last;
    private long modificationCount = 0;

    /**
     * Class to emulate object with references to previous and next object in the list.
     */
    private static class ListNode {
        /**
         * value -> value of object stored in node
         * previous -> reference to previous node is list
         * next -> reference to next node in the list
         */
        private Object value;
        private ListNode previous;
        private ListNode next;
    }

    /**
     * Constructor used for list-collection
     * @param other is collection used to copy elements in this new collection
     */
    public LinkedListIndexedCollection(Collection other) {
        this.addAll(other);
    }

    /**
     * Another constructor
     */
    public LinkedListIndexedCollection() {
        this.modificationCount++;
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * This method is used to determine the size of collection.
     *
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *     // do something
     *     int numOfElements = col.size();
     * </pre>
     * @return numOfElements
     * @since 1.0
     */
    @Override
    public int size() {
        int num = 0;
        ListNode current = first;
        while(current != null) {
            num++;
            current = current.next;
        }
        return num;
    }

    /**
     * This method adds Object type param to the collection.
     *
     * * Example of use:
     *  <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *      // do something
     *      col.add(value);
     *  </pre>
     * @param value value that needs to be added to the collection.
     * @since 1.0
     */
    @Override
    public void add(Object value) {
        if(value != null) {
            this.modificationCount++;
            ListNode newNode = new ListNode();
            newNode.value = value;

            if(first == null) {
                first = last = newNode;
            } else {
                last.next = newNode;
                newNode.previous = last;
                last = newNode;
            }
            this.size++;
        } else {
            throw new NullPointerException("msg");
        }
    }

    /**
     *This method is used to determine whether is object contained in collection.
     *
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
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
        if (value == null) {
            return false;
        }
        ListNode current = first;
        while(current != null) {
            if (value.equals(current.value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * This method is used to remove object from the collection.
     *
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *     //do something
     *     col.remove(value);
     * </pre>
     * @param value value that needs to be removed from the collection
     * @return <code>true</code> if object is removed, else <code>false</code>
     * @since 1.0
     */
    @Override
    public boolean remove(Object value) {
        if(this.isEmpty() || !this.contains(value)) {
            throw new NoSuchElementException("msg");
        }
        this.modificationCount++;
        ListNode current;
        for (current = this.first; current != null; current = current.next) {
            if (current.value.equals(value)) {
                ListNode previous = current.previous;
                ListNode next = current.next;

                if (this.size == 1) {
                    this.first = this.last = null;
                } else if (previous == null) {
                    this.first = next;
                    next.previous = null;
                } else if (next == null) {
                    previous.next = null;
                    this.last = previous;
                } else {
                    previous.next = next;
                    next.previous = previous;
                }
                this.size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all elements from this collection.
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *     //do something
     *     col.clear();
     * </pre>
     * @since 1.0
     */
    @Override
    public void clear() {
        this.modificationCount++;
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * This method is used to get object from collection on index.
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *     //do something
     *     ListNode temp = col.get(index);
     * </pre>
     *
     * @param index index of object which we want to get from collection
     * @return object if index is valid
     * @throws IndexOutOfBoundsException if <code>index</code> is out of bounds.
     * @since 1.0
     */
    public Object get(int index) {
        if(index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("msg");
        } else {
            Object obj;
            int i = 0;
            ListNode current = first;
            for (; current != null; i++) {
                if (i == index) {
                    obj = current.value;
                    return obj;
                }
                current = current.next;
            }
        }
        return null;
    }

    /**
     * This method searches the collection and returns the index of the first occurrence of the given value.
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *      //Do something
     *      int indexed = col.indexOf(value);
     *  </pre>
     *
     * @param value value of object that we are searching for
     * @return <code>-1</code> if object is not found, else <code>index</code> of given value in array.
     * @since 1.0
     */
    public int indexOf(Object value) {
        ListNode current = first;
        int i;
        for(i = 0; current != null; i++) {
            if(current.value.equals(value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * This method removes element at specified index from collection and applies
     * shifting of objects in list.
     *
     * Example of use:
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *      //Do something
     *      col.remove(index);
     * </pre>
     * @param index on which index in array we want to remove object
     * @throws IndexOutOfBoundsException for invalid index
     */

    public void remove(int index) {
        if(index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("msg");
        } else {
            this.modificationCount++;
            ListNode current = first;
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            this.remove(current.value);
            this.size--;
        }
    }

    /**
     * This method inserts given value at the given position in list with shifting.
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *     //do something
     *     col.insert(value, index);
     * </pre>
     * @param value value of object that we want to insert
     * @param position position on which we want to insert the object
     * @since 1.0
     */

    public void insert(Object value, int position) {
        if(position >= 1 && position <= this.size) {
            this.modificationCount++;
            ListNode newNode = new ListNode();
            newNode.value = value;
            ListNode current = first;
            this.size++;

            if(position == 1) {
                newNode.next = first;
                newNode.previous = null;
                first.previous = newNode;
                first = newNode;
            } else if(position < this.size) {
                int i = 1;
                while(current != null) {
                    if(i == position) {
                        break;
                    }
                    i++;
                    current = current.next;
                }
                newNode.next = current.previous.next;
                newNode.previous = current.previous;
                current.previous.next = newNode;
                newNode.next.previous = newNode;
            } else {
                last.next = newNode;
                newNode.previous = last;
            }
        } else {
            throw new IndexOutOfBoundsException("msg");
        }
    }

    /**
     * This method is used to make an array out of collection.
     * Example of use:
     * <pre>
     *     LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *     //do something
     *     Object[] array = col.toArray();
     * </pre>
     * @return Object[]
     * @since 1.0
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        ListNode current = first;
        int i = 0;
        while(current != null) {
            array[i++] = current.value;
            current = current.next;
        }
        return array;
    }

    /**
     * This method adds all elements from other collection to this collection
     *
     * Example of use:
     * <pre>
     *      LinkedListIndexedCollection col = new LinkedListIndexedCollection();
     *      col.addAll(other);
     * </pre>
     * @param other collection we want to add
     */
    @Override
    public void addAll(Collection other) {
        LinkedListIndexedCollection collection = new LinkedListIndexedCollection(other);
        Object[] array = collection.toArray();
        for (Object o : array) {
            this.add(o);
        }
    }



//    public void print() {
//        ListNode current = first;
//        while(current != null) {
//            System.out.println(current.value);
//            current = current.next;
//        }
//    }

    /**
     * Class used for accessing linked-list type collection
     */
    private static class linkedListIndexedElementsGetter implements ElementsGetter {
        LinkedListIndexedCollection list;
        ListNode node;
        long savedModificationCount;
        /**
         * Constructor for linkedListIndexedElementsGetter
         * @param other collection which we are iterating
         * @param modificationCount helps us know how many times we have modified collection
         * node is helper variable used for comparison
         */
        public linkedListIndexedElementsGetter(LinkedListIndexedCollection other, long modificationCount) {
            this.list = other;
            this.savedModificationCount = modificationCount;
            this.node = this.list.first;
        }

        /**
         * method used to determine if we have any more elements in collection
         *      linkedListIndexedElementsGetter eg = new linkedListIndexedElementsGetter();
         *      if(eg.hasNextElement()) {
         *          //do something
         *      }
         * @return <code>true</code> if there exists next element, <code>false</code> otherwise
         * @throws ConcurrentModificationException if we have modified the collection
         */
        @Override
        public boolean hasNextElement() {
            if(this.savedModificationCount != list.modificationCount) {
                throw new ConcurrentModificationException("msg");
            }
            return node != null;
        }

        /**
         * method used to get next elements in collection
         *      linkedListIndexedElementsGetter eg = new linkedListIndexedElementsGetter();
         *      if(eg.hasNextElement()) {
         *          eg.getNextElement();
         *      }
         * @return Object if he manages to get the element
         * @throws ConcurrentModificationException if we have modified the collection
         * @throws NoSuchElementException if there is no more elements in collection
         */
        @Override
        public Object getNextElement() {
            if(this.savedModificationCount != list.modificationCount) {
                throw new ConcurrentModificationException("msg");
            }
            if(this.hasNextElement()) {
                Object obj = node.value;
                node = node.next;
                return obj;
            } else {
                throw new NoSuchElementException("msg");
            }
        }
    }
    /**
     * ElementsGetter constructor
     * @return linkedListIndexedElementsGetter
     */
    @Override
    public ElementsGetter createElementsGetter() {
        return new linkedListIndexedElementsGetter(this, modificationCount);
    }
}

package hr.fer.oprpp1.custom.collections;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K,V>> {
    private TableEntry<K, V>[] table;
    private int size = 0;
    private int modificationCount = 0;

    @SuppressWarnings("unchecked")
    public SimpleHashtable() {
        this.table = (TableEntry<K, V>[]) new TableEntry[16];
    }


    @SuppressWarnings("unchecked")
    public SimpleHashtable(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("msg");
        } else {
            int capacity;
            int power = (int) Math.floor(Math.sqrt(initialCapacity));
            capacity = (int) Math.pow(2, power);
            this.table = (TableEntry<K, V>[]) new TableEntry[capacity];
        }
    }

    public static class TableEntry<K, V> {
        private K key;
        private V value;
        private TableEntry<K, V> next;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public int getLength() {
        return table.length;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("msg");
        } else {
            for (int i = 0; i < this.table.length - 1; i++) {
                TableEntry<K, V> itElem = this.table[i];
                while (itElem != null) {
                    if (itElem.key.equals(key)) {
                        V oldValue = itElem.getValue();
                        itElem.setValue(value);
                        return oldValue;
                    }
                    itElem = itElem.next;
                }
            }
            double occupancy = (double) (this.size / this.table.length);

            if (occupancy >= 0.75) {
                TableEntry<K, V>[] temp = this.toArray();
                this.table = (TableEntry<K, V>[]) new TableEntry[this.table.length * 2];
                modificationCount++;
                for (int i = 0; i < temp.length - 1; i++) {
                    this.table[Math.abs(key.hashCode() % (this.table.length - 1))] = temp[i];
                }
            }

            TableEntry<K, V> newElement = new TableEntry<>();
            newElement.key = key;
            newElement.setValue(value);

            int position = Math.abs(key.hashCode() % (this.table.length - 1));
            TableEntry<K, V> entry = this.table[position];

            if (entry == null) {
                this.table[position] = newElement;
            } else {
                while (entry.next != null) {
                    entry = entry.next;
                }
                entry.next = newElement;
            }
            modificationCount++;
            this.size++;
            return null;
        }
    }


    public V get(Object key) {
        if (key == null) {
            return null;
        }
        for (int i = 0; i < this.table.length; i++) {
            if (this.table[i] != null) {
                TableEntry<K, V> itElem = this.table[i];
                while (itElem != null) {
                    if (itElem.key.equals(key)) {
                        return itElem.getValue();
                    }
                    itElem = itElem.next;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException("msg");
        }
        for (int i = 0; i < this.table.length - 1; i++) {
            TableEntry<K, V> itElem = this.table[i];
            while (itElem != null) {
                if (itElem.key.equals(key)) {
                    return true;
                }
                itElem = itElem.next;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < this.size - 1; i++) {
            TableEntry<K, V> itElem = this.table[i];
            while (itElem != null) {
                if (itElem.value == value) {
                    return true;
                }
                itElem = itElem.next;
            }
        }
        return false;
    }

    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        int position = key.hashCode() % (this.table.length - 1);
        TableEntry<K, V> entry = table[position];

        if (entry.key.equals(key)) {
            if (entry.next != null) {
                TableEntry<K, V> temp = entry.next;
                while (temp != null) {
                    this.put(entry.key, entry.value);
                    temp = temp.next;
                }
                table[position] = null;
                modificationCount++;
                size--;
                return entry.getValue();
            }
        }
        TableEntry<K, V> head = entry.next;
        TableEntry<K, V> before = entry;
        while (head != null) {
            if (head.key.equals(key)) {
                before.next = head.next;
                size--;
                modificationCount++;
                return head.getValue();
            }
            before = head;
            head = head.next;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < table.length - 1; i++) {
            TableEntry<K, V> itElem = this.table[i];
            while (itElem != null) {
                sb.append(itElem.key).append("=").append(itElem.value).append(", ");
                itElem = itElem.next;
            }
        }
        if (sb.length() > 2) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public TableEntry<K, V>[] toArray() {
        TableEntry<K, V>[] tbl = (TableEntry<K, V>[]) new TableEntry[this.size];
        int j = 0;
        for (int i = 0; i < table.length - 1; i++) {
            TableEntry<K, V> itElem = this.table[i];
            while (itElem != null) {
                tbl[j++] = itElem;
                itElem = itElem.next;
            }
        }
        return tbl;
    }

    public void clear() {
        Arrays.fill(this.table, null);
        this.size = 0;
        modificationCount++;
    }

    @Override
    public Iterator<TableEntry<K, V>> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>> {
        private int currSize = size;
        private boolean isNext = false;
        private TableEntry<K, V> current = table[0];

        private int modified = modificationCount;

        public boolean hasNext() {
            if(modificationCount != modified) {
                throw new ConcurrentModificationException("Table was modified!");
            }
            return currSize > 0;
        }

        int i = 0;

        public TableEntry<K, V> next() {
            if (currSize < 1) {
                throw new NoSuchElementException("No more elements!");
            }
            if(modificationCount != modified) {
                throw new ConcurrentModificationException("Table was modified!");
            }
            if (current != null && current.next != null) {
                current = current.next;
            } else {
                while (table[i] == null && i < table.length - 1) {
                    i++;
                }
                current = table[i];
            }
            isNext = true;
            currSize--;
            return current;
        }


        public void remove() {
            if(isNext == false) {
                throw new IllegalStateException("message");
            }
            modified++;
            currSize++;
            isNext = false;
            SimpleHashtable.this.remove(current.getKey());
        }
    }
}

package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListIndexedCollectionTest {

    @Test
    void size() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        assertEquals(0, col.size());
        col.add(2);
        col.add("Professor");
        col.add(14);
        assertEquals(3, col.size());
        col.add("Mark");
        col.add(21);
        assertEquals(5, col.size());
    }

    @Test
    void add() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Tesla");
        String str = "Tesla";
        assertSame(str, col.get(0));
        col.add(3);
        assertSame(3, col.get(1));
    }

    @Test
    void addException() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        assertThrows(NullPointerException.class, () -> col.add(null));
    }

    @Test
    void contains() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Arctic Monkeys");
        col.add("Colonia");
        boolean test = col.contains("Colonia");
        assertTrue(test);
        test = col.contains("Mars");
        assertFalse(test);
    }

    @Test
    void remove() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Rolling Stones");
        col.add("Belfast Food");
        assertTrue(col.remove("Belfast Food"));
        assertTrue(col.remove("Rolling Stones"));
        assertEquals(0, col.size());
    }

    @Test
    void clear() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Rolling Stones");
        col.add("Belfast Food");
        col.clear();
        assertEquals(0, col.size());
    }

    @Test
    void toArray() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        Object[] arr = col.toArray();
        for(int i = 0; i < col.size(); i++) {
            assertEquals(col.get(i), arr[i]);
        }
    }
    //TODO
    @Test
    void forEach() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
    }

    @Test
    void get() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        assertEquals("Metallica", col.get(3));
    }

    @Test
    void getException() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        assertThrows(IndexOutOfBoundsException.class, () -> col.get(5));
    }

    @Test
    void insert() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        col.insert("Beach Boys", 1);
        col.insert("Little Richards", 3);
        assertEquals("Beach Boys", col.get(0));
        assertEquals("Little Richards", col.get(2));
    }

    @Test
    void insertException() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        assertThrows(IndexOutOfBoundsException.class, () -> col.insert("Beach Boys", 12));
    }

    @Test
    void indexOf() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        assertEquals(-1, col.indexOf(null));
        assertEquals(2, col.indexOf("Dubliners"));
        assertNotEquals(0, col.indexOf("Dropkick Murphys"));
    }

    @Test
    void testRemove() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Whitesnake");
        col.add("The Beatles");
        col.add("Pearl Jam");
        col.add("Kansas City");
        col.remove(0);
        assertEquals("The Beatles", col.get(0));
        assertEquals("Pearl Jam", col.get(1));
        assertEquals("Kansas City", col.get(2));
    }

    @Test
    void testRemoveException() {
        LinkedListIndexedCollection col = new LinkedListIndexedCollection();
        col.add("Whitesnake");
        col.add("The Beatles");
        col.add("Pearl Jam");
        col.add("Kansas City");
        assertThrows(IndexOutOfBoundsException.class, () -> col.remove(12));
    }
}
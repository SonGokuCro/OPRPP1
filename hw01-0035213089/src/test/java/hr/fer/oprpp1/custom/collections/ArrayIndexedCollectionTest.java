package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIndexedCollectionTest {

    @Test
    void size() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(4);
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
        ArrayIndexedCollection col = new ArrayIndexedCollection(4);
        col.add("Tesla");
        String str = "Tesla";
        assertSame(str, col.get(0));
        col.add(3);
        assertSame(3, col.get(1));
    }

    @Test
    void addException() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        assertThrows(NullPointerException.class, () -> col.add(null));
    }

    @Test
    void contains() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Arctic Monkeys");
        col.add("Colonia");
        boolean test = col.contains("Colonia");
        assertTrue(test);
        test = col.contains("Mars");
        assertFalse(test);
    }

    @Test
    void remove() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Rolling Stones");
        col.add("Belfast Food");
        assertTrue(col.remove("Belfast Food"));
        assertFalse(col.remove("Belfast Food"));

        assertTrue(col.remove("Rolling Stones"));
        assertEquals(0, col.size());
    }

    @Test
    void clear() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Rolling Stones");
        col.add("Belfast Food");
        col.clear();
        assertEquals(0, col.size());
    }

    @Test
    void toArray() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
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
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
    }

    @Test
    void get() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        assertEquals("Metallica", col.get(3));
    }

    @Test
    void getException() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        assertThrows(IndexOutOfBoundsException.class, () -> col.get(6));
    }

    @Test
    void insert() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
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
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Bob Dylan");
        col.add("Dropkick Murphys");
        col.add("Dubliners");
        col.add("Metallica");
        assertThrows(IndexOutOfBoundsException.class, () -> col.insert("Beach Boys", 12));
    }

    @Test
    void indexOf() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
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
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Whitesnake");
        col.add("The Beatles");
        col.add("Pearl Jam");
        col.add("Kansas City");
        col.remove(0);
        assertEquals("The Beatles", col.get(0));
        assertNull(col.get(3));
        assertEquals("Pearl Jam", col.get(1));
    }

    @Test
    void testRemoveException() {
        ArrayIndexedCollection col = new ArrayIndexedCollection(2);
        col.add("Whitesnake");
        col.add("The Beatles");
        col.add("Pearl Jam");
        col.add("Kansas City");
        assertThrows(IndexOutOfBoundsException.class, () -> col.remove(12));
    }
}
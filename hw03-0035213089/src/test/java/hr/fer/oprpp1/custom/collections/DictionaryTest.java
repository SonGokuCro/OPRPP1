package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void isEmpty() {
        Dictionary<String, String> dic = new Dictionary<>();
        assertEquals(0, dic.size());
    }

    @Test
    void size() {
        Dictionary<Integer, Integer> d = new Dictionary <Integer, Integer>();
        d.put(4, 3);
        d.put(2, 1);
        assertEquals(2, d.size());
    }

    @Test
    void clear() {
        Dictionary<Integer, Integer> d = new Dictionary <Integer, Integer>();
        d.put(4, 3);
        d.put(2, 1);
        assertEquals(2, d.size());
        d.clear();
        assertEquals(0, d.size());
    }

    @Test
    void put() {
        Dictionary<Integer, Integer> d = new Dictionary <Integer, Integer>();
        d.put(2, 45);
        d.put(3, 8);
        assertEquals(45, d.get(2));
        assertEquals(8, d.get(3));
        assertThrows(NullPointerException.class, () -> d.put(null, 12));
    }

    @Test
    void get() {
        Dictionary<Integer, Integer> d = new Dictionary <Integer, Integer>();
        d.put(2, 45);
        d.put(3, 8);
        assertEquals(45, d.get(2));
        assertEquals(8, d.get(3));

    }

    @Test
    void remove() {
        Dictionary<Integer, Integer> d = new Dictionary <Integer, Integer>();
        d.put(2, 45);
        d.put(3, 8);
        d.put(1, 21);
        d.put(4, 99);
        d.put(14, 126);
        assertEquals(99,d.remove(4));
        assertEquals(null, d.get(4));
        assertEquals(4, d.size());
    }
}
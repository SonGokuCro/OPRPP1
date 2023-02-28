package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleHashtableTest {
	
	@Test
	public void testConstructorinitialCapacityInvalid() {
		assertThrows(IllegalArgumentException.class, () -> new SimpleHashtable<>(-1));
	}
	
	@Test
	public void testConstructorinitialCapacityValid() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(30);
		assertTrue(s1.isEmpty());
		assertEquals(32, s1.getLength());
	}
	
	@Test
	public void testPutKeyNull() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		assertThrows(NullPointerException.class,() -> s1.put(null, 1));
	}
	
	@Test
	public void testPutNotInHashTable() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", null);
		s1.put("Neos", 3);
		assertNull(s1.get(""));
		assertEquals(3,s1.get("Neos"));
	}
	
	@Test
	public void testPutInHashTable() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", null);
		s1.put("", 5);
		//TODO
//		s1.put("", 2);
		assertEquals(5,s1.get(""));
	}
	
	@Test
	public void testGetNotInInHashTable() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("",2);
		assertEquals(2,s1.get(""));
	}
	
	@Test
	public void testGetKeyInHashTable() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		assertNull(s1.get("a"));
	}
	
	@Test
	public void testSize() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 1);
		s1.put("ga", 3);
		assertEquals(2,s1.size());
	}
	
	@Test
	public void testIsEmptyFalse() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		assertFalse(s1.isEmpty());
	}
	
	@Test
	public void testIsEmptyTrue() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		assertTrue(s1.isEmpty());
	}
	
	@Test
	public void testContainsKeyNull() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		assertThrows(NullPointerException.class,() -> s1.containsKey(null));
	}
	
	@Test
	public void testContainsKeytrue() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		assertTrue(s1.containsKey(""));
	}
	
	@Test
	public void testContainsKeyfalse() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		assertFalse(s1.containsKey("a"));
	}
	
	@Test
	public void testContainsValuefalse() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		s1.put("a", 1);
		s1.put("b", 2);
		s1.put("c", 2);
		assertFalse(s1.containsValue(3));
	}
	
	@Test
	public void testContainsValueTrue() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		s1.put("a", 1);
		s1.put("b", 2);
		s1.put("c", 2);
		assertTrue(s1.containsValue(1));
	}
	
	@Test
	public void testRemoveKeyNotInHashtable() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		s1.put("a", 1);
		s1.put("b", 2);
		s1.put("c", 2);
		assertNull(s1.remove("d"));
	}
	
	@Test
	public void testRemoveKeyInHashtable() {
		SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(5);
		s1.put("", 2);
		s1.put("a", 1);
		s1.put("b", 2);
		s1.put("c", 2);
		assertEquals(2,s1.remove("c"));
	}

}

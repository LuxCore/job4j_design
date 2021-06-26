package ru.job4j.set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleSetTest {

	@Test
	void whenAddNonNull() {
		Set<String> set = new SimpleSet<>();
		assertAll(
				() -> assertTrue(set.add("One")),
				() -> assertTrue(set.contains("One")),
				() -> assertFalse(set.add("One")),
				() -> assertTrue(set.add("69")),
				() -> assertTrue(set.contains("69")),
				() -> assertFalse(set.add("69"))
		);
	}

	@Test
	void whenAddNull() {
		Set<String> set = new SimpleSet<>();
		assertAll(
				() -> assertTrue(set.add(null)),
				() -> assertTrue(set.contains(null)),
				() -> assertFalse(set.add(null))
		);
	}

	@Test
	void whenContainsAddedElements() {
		Set<Integer> set = new SimpleSet<>();
		set.add(1);
		set.add(1);
		set.add(2);
		set.add(null);
		set.add(69);
		set.add(69);
		assertAll(
				() -> assertEquals(4, set.size()),
				() -> assertTrue(set.contains(1)),
				() -> assertTrue(set.contains(2)),
				() -> assertTrue(set.contains(69)),
				() -> assertTrue(set.contains(null))
		);
	}

	@Test
	void iterator() {
		Set<Integer> set = new SimpleSet<>();
		set.add(1);
		set.add(1);
		set.add(2);
		set.add(null);
		set.add(69);
		set.add(69);
		Iterator<Integer> it = set.iterator();
		assertAll(
				() -> assertTrue(it.hasNext()),
				() -> assertEquals(1, it.next()),
				() -> assertEquals(2, it.next()),
				() -> assertNull(it.next()),
				() -> assertEquals(69, it.next()),
				() -> assertFalse(it.hasNext()),
				() -> assertThrows(NoSuchElementException.class, it::next)
		);
	}
}
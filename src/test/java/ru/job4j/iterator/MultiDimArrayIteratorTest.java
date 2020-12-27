package ru.job4j.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test two dimensional array iterator")
class MultiDimArrayIteratorTest {
	@Test
	@DisplayName("should return all elements")
	void testNextWhenArrayHasEmptyAndNonEmptyArrays() {
		int[][] in = {
				{}, {11, 12}, {}, {31}, {},
				{51, 52, 53}, {}, {71, 72}
		};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertAll(
				() -> assertEquals(11, it.next()),
				() -> assertEquals(12, it.next()),
				() -> assertEquals(31, it.next()),
				() -> assertEquals(51, it.next()),
				() -> assertEquals(52, it.next()),
				() -> assertEquals(53, it.next()),
				() -> assertEquals(71, it.next()),
				() -> assertEquals(72, it.next()),
				() -> assertFalse(it.hasNext())
		);
	}

	@Test
	@DisplayName("should return value from single-element array")
	void testNextWhenArrayHasSingleElementArray() {
		int[][] in = {{1}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertEquals(1, it.next());
	}

	@Test
	@DisplayName("should return second element when first array is empty")
	void whenFirstElementIsEmptyThenNext() {
		int[][] in = {{}, {1}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertEquals(1, it.next());
	}

	@Test
	@DisplayName("should return true when first element is empty")
	void whenFirstElementIsEmptyThenHashNext() {
		int[][] in = {{}, {1}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertTrue(it.hasNext());
	}

	@Test
	@DisplayName("should return three values from two rows")
	void whenRowsHaveDifferentSize() {
		int[][] in = {{1}, {2, 3}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertAll(
				() -> assertEquals(1, it.next()),
				() -> assertEquals(2, it.next()),
				() -> assertEquals(3, it.next())
		);
	}

	@Test
	@DisplayName("should return two values when few empty rows one by one")
	void whenFewRowsAreEmpty() {
		int[][] in = {{1}, {}, {}, {}, {2}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertAll(
				() -> assertEquals(1, it.next()),
				() -> assertEquals(2, it.next())
		);
	}

	@Test
	@DisplayName("should return false when array is empty")
	void whenArrayIsEmpty() {
		int[][] in = {{}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertFalse(it.hasNext());
	}

	@Test
	@DisplayName("should throw the exception when array has no elements")
	void whenArrayIsEmptyThenNextThrowsException() {
		int[][] in = {{}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertThrows(NoSuchElementException.class, it::next);
	}

	@Test
	@DisplayName("should return false after next returned last element")
	void testHasNextWithDifferentResult() {
		int[][] in = {{}, {1}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertAll(
				() -> assertTrue(it.hasNext()),
				() -> assertEquals(1, it.next()),
				() -> assertFalse(it.hasNext())
		);
	}

	@Test
	@DisplayName("should return false when all elements are empty")
	void testHasNextWhenAllElementAreEmpty() {
		int[][] in = {{}, {}, {}};
		MultiDimArrayIterator it = new MultiDimArrayIterator(in);
		assertFalse(it.hasNext());
	}
}
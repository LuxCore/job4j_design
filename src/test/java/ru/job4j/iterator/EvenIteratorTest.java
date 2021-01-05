package ru.job4j.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EvenIteratorTest {
	@Test
	@DisplayName("should return few evens")
	void testNextWhenNumbersLocateWithNormalOrder() {
		int[] data = {1, 2, 3, 4};
		EvenIterator it = new EvenIterator(data);
		assertAll(
				() -> assertEquals(2, it.next()),
				() -> assertEquals(4, it.next()),
				() -> assertFalse(it.hasNext())
		);
	}

	@Test
	@DisplayName("should return even between odds")
	void testNextWhenEvenBetweenOdds() {
		int[] data = {1, 3, 4, 3, 1};
		EvenIterator it = new EvenIterator(data);
		assertAll(
				() -> assertEquals(4, it.next()),
				() -> assertFalse(it.hasNext())
		);
	}

	@Test
	@DisplayName("should return few evens between odds")
	void testNextWhenEvensBetweenOdds() {
		int[] data = {1, 3, 4, 6, 8, 3, 1};
		EvenIterator it = new EvenIterator(data);
		assertAll(
				() -> assertEquals(4, it.next()),
				() -> assertEquals(6, it.next()),
				() -> assertEquals(8, it.next()),
				() -> assertFalse(it.hasNext())
		);
	}

	@Test
	@DisplayName("should return few evens from end points")
	void testNextWhenEvensAreEndpoints() {
		int[] data = {8, 6, 5, 3, 1, 4, 2};
		EvenIterator it = new EvenIterator(data);
		assertAll(
				() -> assertEquals(8, it.next()),
				() -> assertEquals(6, it.next()),
				() -> assertEquals(4, it.next()),
				() -> assertEquals(2, it.next()),
				() -> assertFalse(it.hasNext())
		);
	}

	@Test
	@DisplayName("should return evens from singleton array")
	void testNextWhenArrayHasOneEven() {
		int[] data = {6};
		EvenIterator it = new EvenIterator(data);
		assertAll(
				() -> assertEquals(6, it.next()),
				() -> assertFalse(it.hasNext())
		);
	}

	@Test
	@DisplayName("should throw NoSuchElementException in singleton array")
	void testNextWhenNoEvens() {
		int[] data = {1};
		EvenIterator it = new EvenIterator(data);
		assertThrows(NoSuchElementException.class, it::next);
	}

	@Test
	@DisplayName("should throw NoSuchElementException from empty array")
	void testNextArrayIsEmpty() {
		int[] data = {};
		EvenIterator it = new EvenIterator(data);
		assertThrows(NoSuchElementException.class, it::next);
	}

	@Test
	@DisplayName("should false from singleton array")
	void testHasNextWhenArrayHasOneOdd() {
		int[] data = {1};
		EvenIterator it = new EvenIterator(data);
		assertFalse(it.hasNext());
	}

	@Test
	@DisplayName("should false from array of odds")
	void testHasNextWhenArrayHasOnlyOdds() {
		int[] data = {1, 3, 5, 7, 9, 11};
		EvenIterator it = new EvenIterator(data);
		assertFalse(it.hasNext());
	}
}
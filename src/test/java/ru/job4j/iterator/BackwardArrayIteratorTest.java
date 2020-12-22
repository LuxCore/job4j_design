package ru.job4j.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("When iterator traverses the array in reverse order")
class BackwardArrayIteratorTest {
	@Test
	@DisplayName("should return true when array checked for next element")
	void testHasNextElement() {
		BackwardArrayIterator arrIt = new BackwardArrayIterator(
				new int[] {1, 2, 3}
		);
		assertAll(
				() -> assertTrue(arrIt.hasNext()),
				() -> {
					arrIt.next();
					assertTrue(arrIt.hasNext());
				},
				() -> {
					arrIt.next();
					assertTrue(arrIt.hasNext());
				}
		);
	}

	@Test
	@DisplayName("should return array values in reverse order")
	void testNextElement() {
		BackwardArrayIterator arrIt = new BackwardArrayIterator(
				new int[] {1, 2, 3}
		);
		assertAll(
				() -> assertEquals(3, arrIt.next()),
				() -> assertEquals(2, arrIt.next()),
				() -> assertEquals(1, arrIt.next())
		);
	}

	@Test
	@DisplayName("throws NoSuchElementException when it is no next element")
	void nextElementThrowsNoSuchElementException() {
		BackwardArrayIterator arrIt = new BackwardArrayIterator(new int[] {});
		assertThrows(NoSuchElementException.class, arrIt::next);
	}
}
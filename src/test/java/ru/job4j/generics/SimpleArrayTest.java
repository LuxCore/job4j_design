package ru.job4j.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Simple array")
class SimpleArrayTest {

	@Test
	@DisplayName("should create object by size")
	void constructBySize() {
		SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
		simpleArray.add(0);
		simpleArray.add(1);
		simpleArray.add(2);
		simpleArray.add(3);
		simpleArray.add(4);
		assertAll(
				() -> assertThat(simpleArray.get(0), is(equalTo(0))),
				() -> assertThat(simpleArray.get(4), is(equalTo(4))),
				() -> assertEquals("[0, 1, 2, 3, 4, null, null, null, null, null]", simpleArray.toString())
		);
	}

	@Test
	@DisplayName("should add elements and remaining elements must be null")
	void addElements() {
		SimpleArray<Integer> simpleArray = new SimpleArray<>();
		simpleArray.add(0);
		simpleArray.add(1);
		simpleArray.add(2);
		simpleArray.add(3);
		simpleArray.add(4);
		simpleArray.add(5);
		simpleArray.add(6);
		simpleArray.add(7);
		assertAll(
				() -> assertThat(simpleArray.get(0), is(equalTo(0))),
				() -> assertThat(simpleArray.get(4), is(equalTo(4))),
				() -> assertThat(simpleArray.get(7), is(equalTo(7)))
		);
	}

	@Test
	void addMoreElementsThanArrayLength() {
		SimpleArray<String> simpleArray = new SimpleArray<>();
		for (int i = 0; i < 13; i++) {
			simpleArray.add(String.valueOf(i));
		}
		assertAll(
				() -> assertThat(simpleArray.get(0), is(equalTo("0"))),
				() -> assertThat(simpleArray.get(12), is(equalTo("12")))
		);
	}

	@Test
	@DisplayName("should set elements by index successfully")
	void set() {
		SimpleArray<Integer> simpleArray = new SimpleArray<>();
		simpleArray.add(0);
		simpleArray.add(1);
		simpleArray.add(2);
		simpleArray.add(3);
		simpleArray.add(4);
		simpleArray.add(5);
		simpleArray.add(6);
		simpleArray.set(0, 69);
		simpleArray.set(4, 69);
		simpleArray.set(6, 69);
		assertAll(
				() -> assertThat(simpleArray.get(0), is(equalTo(69))),
				() -> assertThat(simpleArray.get(4), is(equalTo(69))),
				() -> assertThat(simpleArray.get(6), is(equalTo(69)))
		);
	}

	@Test
	@DisplayName("set should throw IndexOutOfBounds")
	void setThrowIndexOutOfBounds() {
		SimpleArray<Integer> simpleArray = new SimpleArray<>();
		simpleArray.add(1);
		simpleArray.set(0, 69);
		assertAll(
				() -> assertThat(simpleArray.get(0), is(equalTo(69))),
				() -> assertThrows(IndexOutOfBoundsException.class, () -> simpleArray.set(1, 1))
		);
	}

	@Test
	@DisplayName("should remove element successfully shifting elements right to the left")
	void remove() {
		SimpleArray<String> simpleArray = new SimpleArray<>();
		for (int i = 0; i < 10; i++) {
			simpleArray.add(String.valueOf(i));
		}
		assertAll(
				() -> assertThat(simpleArray.remove(1), is(equalTo("1"))),
				() -> assertThat(simpleArray.remove(2), is(equalTo("3"))),
				() -> assertThat(simpleArray.remove(5), is(equalTo("7"))),
				() -> assertEquals("[0, 2, 4, 5, 6, 8, 9, null, null, null]", simpleArray.toString()),
				() -> assertThat(simpleArray.remove(0), is(equalTo("0"))),
				() -> assertThat(simpleArray.remove(0), is(equalTo("2"))),
				() -> assertThat(simpleArray.remove(0), is(equalTo("4"))),
				() -> assertThat(simpleArray.remove(0), is(equalTo("5"))),
				() -> assertThat(simpleArray.remove(0), is(equalTo("6"))),
				() -> assertThat(simpleArray.remove(0), is(equalTo("8"))),
				() -> assertThat(simpleArray.remove(0), is(equalTo("9"))),
				() -> assertEquals("[null, null, null, null, null, null, null, null, null, null]", simpleArray.toString())
		);
	}

	@Test
	@DisplayName("should throw IndexOutOfBounds")
	void whenRemoveIndexIsGreaterThanNumberOfElements() {
		SimpleArray<String> simpleArray = new SimpleArray<>();
		for (int i = 0; i < 3; i++) {
			simpleArray.add(String.valueOf(i));
		}
		assertThrows(IndexOutOfBoundsException.class, () -> simpleArray.remove(3));
	}

	@Test
	void iterator() {
		SimpleArray<String> simpleArray = new SimpleArray<>();
		for (int i = 0; i < 3; i++) {
			simpleArray.add(String.valueOf(i));
		}
		Iterator<String> actual = simpleArray.iterator();
		assertAll(
				() -> assertTrue(actual.hasNext()),
				() -> assertThat(actual.next(), is(equalTo("0"))),
				() -> assertThat(actual.next(), is(equalTo("1"))),
				() -> assertThat(actual.next(), is(equalTo("2"))),
				() -> assertFalse(actual.hasNext()),
				() -> assertThrows(NoSuchElementException.class, actual::next)
		);
	}
}
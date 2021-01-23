package ru.job4j.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Flat map")
class FlatMapTest {
	@Test
	@DisplayName("should return elements after first empty iterator")
	public void whenFirstIteratorIsEmpty() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1, 2, 3).iterator(),
				Collections.<Integer>emptyIterator(),
				List.of(4, 5).iterator()
		).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertAll(
				() -> assertThat(flat.next(), is(equalTo(1))),
				() -> assertThat(flat.next(), is(equalTo(2))),
				() -> assertThat(flat.next(), is(equalTo(3))),
				() -> assertThat(flat.next(), is(equalTo(4))),
				() -> assertThat(flat.next(), is(equalTo(5))),
				() -> assertFalse(flat.hasNext())
		);
	}

	@Test
	@DisplayName("should return all elements")
	public void whenAllInnerIteratorsAreFilled() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1).iterator(),
				List.of(2, 3).iterator()
		).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertAll(
				() -> assertThat(flat.next(), is(equalTo(1))),
				() -> assertThat(flat.next(), is(equalTo(2))),
				() -> assertThat(flat.next(), is(equalTo(3))),
				() -> assertFalse(flat.hasNext())
		);
	}

	@Test
	@DisplayName("should return all elements from single inner iterator")
	public void whenSingleInnerIterator() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1, 2, 3).iterator()
		).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertAll(
				() -> assertThat(flat.next(), is(equalTo(1))),
				() -> assertThat(flat.next(), is(equalTo(2))),
				() -> assertThat(flat.next(), is(equalTo(3))),
				() -> assertFalse(flat.hasNext())
		);
	}

	@Test
	@DisplayName("several hasNext methods in a row should always return true")
	public void whenSingleInnerIteratorHasSingleElement() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1).iterator()
		).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertAll(
				() -> assertTrue(flat.hasNext()),
				() -> assertTrue(flat.hasNext()),
				() -> assertThat(flat.next(), is(equalTo(1))),
				() -> assertFalse(flat.hasNext()),
				() -> assertFalse(flat.hasNext())
		);
	}

	@Test
	@DisplayName("should return false from single iterator with single element")
	public void whenHasNextFalse() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1).iterator()
		).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertAll(
				() -> assertThat(flat.next(), is(1)),
				() -> assertFalse(flat.hasNext())
		);
	}

	@Test
	@DisplayName("should return false from empty single inner iterator")
	public void whenEmpty() {
		Iterator<Iterator<Integer>> data = List.of(
				Collections.<Integer>emptyIterator()
		).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertThrows(NoSuchElementException.class, flat::next);
	}

	@Test
	@DisplayName("when few empty iterators are at the begin, end and inside")
	public void whenFewEmptyIteratorsEverywhere() {
		Iterator<Iterator<Integer>> data = List.of(
				Collections.<Integer>emptyIterator(),
				Collections.<Integer>emptyIterator(),
				Collections.<Integer>emptyIterator(),
				List.of(1, 2, 3).iterator(),
				Collections.<Integer>emptyIterator(),
				Collections.<Integer>emptyIterator(),
				Collections.<Integer>emptyIterator(),
				List.of(4, 5).iterator(),
				Collections.<Integer>emptyIterator(),
				Collections.<Integer>emptyIterator(),
				Collections.<Integer>emptyIterator()
				).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertAll(
				() -> assertThat(flat.next(), is(equalTo(1))),
				() -> assertThat(flat.next(), is(equalTo(2))),
				() -> assertThat(flat.next(), is(equalTo(3))),
				() -> assertThat(flat.next(), is(equalTo(4))),
				() -> assertThat(flat.next(), is(equalTo(5))),
				() -> assertFalse(flat.hasNext())
		);
	}
}
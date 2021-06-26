package ru.job4j.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilsTest {
	@Test
	void whenAddBeforeIntoListWithOneElement() {
		List<Integer> input = new ArrayList<>();
		input.add(1);
		ListUtils.addBefore(input, 0, 2);
		assertEquals(Arrays.asList(2, 1), input);
	}

	@Test
	void whenAddBeforeIntoListWithFewElements() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		ListUtils.addBefore(input, 0, 5);
		assertEquals(Arrays.asList(5, 1, 2, 3, 4), input);
		ListUtils.addBefore(input, 3, 6);
		assertEquals(Arrays.asList(5, 1, 2, 6, 3, 4), input);
		ListUtils.addBefore(input, 5, 7);
		assertEquals(Arrays.asList(5, 1, 2, 6, 3, 7, 4), input);
	}

	@Test
	void whenAddNullsBeforeIntoListWithFewElements() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		ListUtils.addBefore(input, 1, null);
		assertEquals(Arrays.asList(1, null, 2, 3, 4), input);
	}

	@Test
	void whenAddBeforeIntoEmptyListThanEx() {
		List<Integer> input = new ArrayList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addBefore(input, 0, 1));
	}

	@Test
	void whenAddBeforeWithInvalidIndexThanEx() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
		assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addBefore(input, 3, 4));
	}

	@Test
	void whenAddAfterIntoListWithOneElement() {
		List<Integer> input = new ArrayList<>();
		input.add(1);
		ListUtils.addAfter(input, 0, 2);
		assertEquals(Arrays.asList(1, 2), input);
	}

	@Test
	void whenAddAfterIntoListWithFewElements() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		ListUtils.addAfter(input, 0, 5);
		assertEquals(Arrays.asList(1, 5, 2, 3, 4), input);
		ListUtils.addAfter(input, 3, 6);
		assertEquals(Arrays.asList(1, 5, 2, 3, 6, 4), input);
		ListUtils.addAfter(input, 5, 7);
		assertEquals(Arrays.asList(1, 5, 2, 3, 6, 4, 7), input);
	}

	@Test
	void whenAddNullsAfterIntoListWithFewElements() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		ListUtils.addAfter(input, 2, null);
		assertEquals(Arrays.asList(1, 2, 3, null, 4), input);
	}

	@Test
	void whenAddAfterIntoEmptyListThanEx() {
		List<Integer> input = new ArrayList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addAfter(input, 0, 1));
	}

	@Test
	void whenAddAfterWithInvalidIndexThanEx() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
		assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addAfter(input, 3, 4));
	}

	@Test
	void whenRemoveIfNumberIsOdd() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		ListUtils.removeIf(input, (n) -> n % 2 > 0);
		assertEquals(Arrays.asList(2, 4, 6), input);
	}

	@Test
	void removeIfNull() {
		List<Integer> input = new ArrayList<>(Arrays.asList(null, 2, null, null, 5, 6, null));
		ListUtils.removeIf(input, Objects::isNull);
		assertEquals(Arrays.asList(2, 5, 6), input);
	}

	@Test
	void whenRemoveIfListHasOneElement() {
		List<String> input = new ArrayList<>();
		input.add("One");
		ListUtils.removeIf(input, "One"::equals);
		assertEquals(Collections.emptyList(), input);
	}

	@Test
	void whenReplaceIfNumberIsOdd() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		ListUtils.replaceIf(input, (n) -> n % 2 > 0, 69);
		assertEquals(Arrays.asList(69, 2, 69, 4, 69, 6, 69), input);
	}

	@Test
	void replaceIfNullWithDefault() {
		List<Integer> input = new ArrayList<>(Arrays.asList(null, 2, null, 4, 5, null, 7));
		ListUtils.replaceIf(input, Objects::isNull, 0);
		assertEquals(Arrays.asList(0, 2, 0, 4, 5, 0, 7), input);
	}

	@Test
	void whenRemoveAllAndRemainElements() {
		List<String> input = new ArrayList<>(
				Arrays.asList("Один", "2", null, "Три", "4", null, "Пять", "6", null, "Семь"));
		List<String> elements = new ArrayList<>(
				Arrays.asList("Один", "Три", "Пять", "Семь", null));
		ListUtils.removeAll(input, elements);
		assertEquals(Arrays.asList("2", "4", "6"), input);
	}

	@Test
	void whenRemoveAllFromSingleElementListAndNothingRemains() {
		List<String> input = new ArrayList<>();
		input.add("Один");
		List<String> elements = new ArrayList<>();
		elements.add("Один");
		elements.add("Two");
		ListUtils.removeAll(input, elements);
		assertEquals(Collections.emptyList(), input);
	}
}
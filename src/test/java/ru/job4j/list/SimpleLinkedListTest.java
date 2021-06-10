package ru.job4j.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.job4j.generics.strore.User;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleLinkedListTest {
	@Test
	@DisplayName("should return 0 elements")
	void whenListIsEmpty() {
		SimpleLinkedList<User> users = new SimpleLinkedList<>();
		assertAll(
				() -> assertEquals(0, users.size()),
				() -> assertThrows(IndexOutOfBoundsException.class, () -> users.get(0)),
				() -> assertNull(users.getFirst()),
				() -> assertNull(users.getLast())
		);
	}

	@Test
	@DisplayName("should return right size after adding elements")
	void whenAddElements() {
		SimpleLinkedList<User> users = new SimpleLinkedList<>();
		users.add(new User("User1"));
		assertEquals(1, users.size());
		users.add(new User("User2"));
		assertEquals(2, users.size());
		users.add(new User("User3"));
		assertEquals(3, users.size());
		users.add(new User("User4"));
		assertEquals(4, users.size());
	}

	@Test
	void get1() {
		SimpleLinkedList<String> strings = new SimpleLinkedList<>();
		strings.add("String");
		assertAll(
				() -> assertEquals("String", strings.get(0)),
				() -> assertEquals(1, strings.size()),
				() -> assertThrows(IndexOutOfBoundsException.class, () -> strings.get(1))
		);
	}

	@Test
	void get2() {
		SimpleLinkedList<Integer> ints = new SimpleLinkedList<>();
		ints.add(0);
		ints.add(1);
		ints.add(2);
		ints.add(3);
		assertAll(
				() -> assertEquals(0, ints.get(0)),
				() -> assertEquals(1, ints.get(1)),
				() -> assertEquals(2, ints.get(2)),
				() -> assertEquals(3, ints.get(3)),
				() -> assertEquals(4, ints.size()),
				() -> assertThrows(IndexOutOfBoundsException.class, () -> ints.get(6))
		);
	}

	@Test
	void iterator0() {
		SimpleLinkedList<User> users = new SimpleLinkedList<>();
		Iterator<User> itr = users.iterator();
		assertAll(
				() -> assertFalse(itr.hasNext()),
				() -> assertThrows(NoSuchElementException.class, itr::next)
		);
	}

	@Test
	void iterator1() {
		SimpleLinkedList<User> users = new SimpleLinkedList<>();
		users.add(new User("User69"));
		Iterator<User> itr = users.iterator();
		assertAll(
				() -> assertTrue(itr.hasNext()),
				() -> assertEquals("User69", itr.next().getId()),
				() -> assertThrows(NoSuchElementException.class, itr::next)
		);
	}

	@Test
	void iteratorMany() {
		SimpleLinkedList<User> users = new SimpleLinkedList<>();
		users.add(new User("User1"));
		users.add(new User("User2"));
		users.add(new User("User3"));
		users.add(new User("User4"));
		Iterator<User> itr = users.iterator();
		assertAll(
				() -> assertTrue(itr.hasNext()),
				() -> assertEquals("User1", itr.next().getId()),
				() -> assertEquals("User2", itr.next().getId()),
				() -> assertEquals("User3", itr.next().getId()),
				() -> assertEquals("User4", itr.next().getId()),
				() -> assertFalse(itr.hasNext()),
				() -> assertThrows(NoSuchElementException.class, itr::next)
		);
	}

	@Test
	void iteratorFailFast() {
		SimpleLinkedList<User> users = new SimpleLinkedList<>();
		users.add(new User("User1"));
		Iterator<User> itr = users.iterator();
		users.add(new User("User2"));
		assertAll(
				() -> assertThrows(ConcurrentModificationException.class, itr::next)
		);
	}
}
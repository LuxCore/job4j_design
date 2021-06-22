package ru.job4j.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SimpleStackTest {

	@Test
	void whenPushThenPop() {
		SimpleStack<Integer> stack = new SimpleStack<>();
		stack.push(1);
		assertEquals(1, stack.pop());
	}
	@Test
	void pushNullValue() {
		SimpleStack<Integer> stack = new SimpleStack<>();
		stack.push(null);
		assertNull(stack.pop());
	}

	@Test
	void whenPushPollThenPushPop() {
		SimpleStack<Integer> stack = new SimpleStack<>();
		stack.push(1);
		stack.pop();
		stack.push(2);
		assertEquals(2, stack.pop());
	}

	@Test
	void pop() {
		SimpleStack<Integer> stack = new SimpleStack<>();
		stack.push(128);
		stack.push(256);
		stack.push(3);
		stack.push(5);
		stack.push(6669);
		assertAll(
				() -> assertEquals(6669, stack.pop()),
				() -> assertEquals(5, stack.pop()),
				() -> assertEquals(3, stack.pop()),
				() -> assertEquals(256, stack.pop()),
				() -> assertEquals(128, stack.pop()),
				() -> assertThrows(NullPointerException.class, stack::pop)
		);
	}

	@Test
	void peek() {
		SimpleStack<Integer> stack = new SimpleStack<>();
		stack.push(1);
		stack.push(6669);
		Executable assertEquals1AfterPeek = () -> assertEquals(1, stack.peek());
		Executable assertEquals6669AfterPeek = () -> assertEquals(6669, stack.peek());
		Executable sizeEquals1 = () -> assertEquals(1, stack.size());
		assertAll(
				() -> assertEquals(2, stack.size()),
				assertEquals6669AfterPeek,
				assertEquals6669AfterPeek,
				() -> assertEquals(6669, stack.pop()),
				sizeEquals1,
				assertEquals1AfterPeek,
				assertEquals1AfterPeek,
				sizeEquals1
		);
	}
}
package ru.job4j.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleStackTest {

	@Test
	public void whenPushThenPoll() {
		SimpleStack<Integer> stack = new SimpleStack<>();
		stack.push(1);
		assertEquals(1, stack.pop());
	}

	@Test
	public void whenPushPollThenPushPoll() {
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
				() -> assertEquals(128, stack.pop())
		);
	}
}
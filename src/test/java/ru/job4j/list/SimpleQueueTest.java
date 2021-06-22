package ru.job4j.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleQueueTest {

	@Test
	void whenQueueIsEmpty() {
		SimpleQueue<Integer> queue = new SimpleQueue<>();
		assertAll(
				() -> assertNull(queue.peek()),
				() -> assertThrows(NullPointerException.class, queue::dequeue)
		);
	}

	@Test
	void addOneElementToQueue() {
		SimpleQueue<String> queue = new SimpleQueue<>();
		queue.enqueue("Value 1");
		assertAll(
				() -> assertEquals("Value 1", queue.peek()),
				() -> assertEquals(1, queue.size()),
				() -> assertEquals("Value 1", queue.dequeue()),
				() -> assertEquals(0, queue.size()),
				() -> assertNull(queue.peek()),
				() -> assertThrows(NullPointerException.class, queue::dequeue)
		);
	}

	@Test
	void addFewElementsToQueue() {
		SimpleQueue<String> queue = new SimpleQueue<>();
		queue.enqueue("Value 1");
		queue.enqueue("Element 2");
		assertEquals("Value 1", queue.dequeue());
		queue.enqueue("Item 3");
		assertEquals("Element 2", queue.peek());
		queue.enqueue("Value 69");
		queue.enqueue("Value 6669");
		assertAll(
				() -> assertEquals(4, queue.size()),
				() -> assertEquals("Element 2", queue.dequeue()),
				() -> assertEquals("Item 3", queue.dequeue()),
				() -> assertEquals("Value 69", queue.dequeue()),
				() -> assertEquals("Value 6669", queue.peek()),
				() -> assertEquals("Value 6669", queue.dequeue()),
				() -> assertNull(queue.peek())
		);
	}

	@Test
	void dequeueAllValues() {
		SimpleQueue<String> queue = new SimpleQueue<>();
		queue.enqueue("Value 1");
		queue.enqueue("Element 2");
		queue.enqueue("Item 3");
		queue.enqueue("Value 69");
		queue.enqueue("Value 6669");
		queue.enqueue("Value 777");
		queue.enqueue("Value 666");
		queue.enqueue("Value 555");
		assertAll(
				() -> assertEquals(8, queue.size()),
				() -> assertEquals("Value 1", queue.dequeue()),
				() -> assertEquals("Element 2", queue.dequeue()),
				() -> assertEquals("Item 3", queue.dequeue()),
				() -> assertEquals("Value 69", queue.dequeue()),
				() -> assertEquals("Value 6669", queue.dequeue()),
				() -> assertEquals("Value 777", queue.dequeue()),
				() -> assertEquals("Value 666", queue.dequeue()),
				() -> assertEquals("Value 555", queue.dequeue()),
				() -> assertEquals(0, queue.size())
		);
	}
}
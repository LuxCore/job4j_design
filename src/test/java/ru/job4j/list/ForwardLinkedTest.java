package ru.job4j.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ForwardLinkedTest {

	@Test
	void addOneElement() {
		ForwardLinked<String> fl = new ForwardLinked<>();
		fl.add("One");
		Iterator<String> it = fl.iterator();
		assertAll(
				() -> assertEquals("One", it.next()),
				() -> assertThrows(NoSuchElementException.class, it::next)
		);
	}

	@Test
	void addNullElement() {
		ForwardLinked<String> fl = new ForwardLinked<>();
		fl.add("One");
		fl.add(null);
		fl.add("3");
		Iterator<String> it = fl.iterator();
		assertAll(
				() -> assertEquals("One", it.next()),
				() -> assertNull(it.next()),
				() -> assertEquals("3", it.next()),
				() -> assertThrows(NoSuchElementException.class, it::next)
		);
	}

	@Test
	void addMultipleElements() {
		ForwardLinked<String> fl = new ForwardLinked<>();
		fl.add("One");
		fl.add("Two");
		fl.add("Three");
		fl.add("6669");
		Iterator<String> it = fl.iterator();
		assertAll(
				() -> assertEquals("One", it.next()),
				() -> assertEquals("Two", it.next()),
				() -> assertEquals("Three", it.next()),
				() -> assertEquals("6669", it.next()),
				() -> assertThrows(NoSuchElementException.class, it::next)
		);
	}

	@Test
	void addDuringIteration() {
		ForwardLinked<Integer> fl = new ForwardLinked<>();
		fl.add(1);
		Iterator<Integer> it = fl.iterator();
		fl.add(6669);
		assertThrows(ConcurrentModificationException.class, it::next);
	}

	@Test
	void addFirst() {
		ForwardLinked<String> fl = new ForwardLinked<>();
		fl.addFirst("First");
		fl.addFirst("Второй");
		fl.addFirst("Third");
		fl.addFirst("Четвёртый");
		fl.addFirst("6669");
		assertAll(
				() -> assertEquals("6669", fl.deleteFirst()),
				() -> assertEquals(4, fl.size()),
				() -> assertEquals("Четвёртый", fl.deleteFirst()),
				() -> assertEquals("Third", fl.deleteFirst()),
				() -> assertEquals("Второй", fl.deleteFirst()),
				() -> assertEquals("First", fl.deleteFirst()),
				() -> assertEquals(0, fl.size())
		);
	}

	@Test
	void deleteFirstWhenEmpty() {
		ForwardLinked<Integer> fl = new ForwardLinked<>();
		assertThrows(NullPointerException.class, fl::deleteFirst);
	}

	@Test
	void deleteFirst() {
		ForwardLinked<Integer> fl = new ForwardLinked<>();
		fl.add(1);
		fl.add(2);
		fl.add(3);
		fl.add(6669);
		assertAll(
				() -> assertEquals(1, fl.deleteFirst()),
				() -> assertEquals(2, fl.deleteFirst()),
				() -> assertEquals(3, fl.deleteFirst()),
				() -> assertEquals(6669, fl.deleteFirst()),
				() -> assertThrows(NullPointerException.class, fl::deleteFirst),
				() -> assertThrows(NoSuchElementException.class, fl.iterator()::next)
		);
	}

	@Test
	void deleteDuringIteration() {
		ForwardLinked<Integer> fl = new ForwardLinked<>();
		fl.add(1);
		fl.add(6669);
		Iterator<Integer> it = fl.iterator();
		fl.deleteFirst();
		assertThrows(ConcurrentModificationException.class, it::next);
	}

	@Test
	void iterateWhenEmpty() {
		ForwardLinked<Integer> fl = new ForwardLinked<>();
		assertThrows(NoSuchElementException.class, fl.iterator()::next);
	}

	@Test
	void peek() {
		ForwardLinked<Integer> fl = new ForwardLinked<>();
		fl.add(1);
		fl.add(6669);
		Executable assertEquals1 = () -> assertEquals(1, fl.peek());
		Executable assertEquals6669 = () -> assertEquals(6669, fl.peek());
		assertAll(
				assertEquals1,
				assertEquals1,
				() -> assertEquals(1, fl.deleteFirst()),
				assertEquals6669,
				assertEquals6669
		);
	}
}
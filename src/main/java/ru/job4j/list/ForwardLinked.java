package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Однонаправленный связный список.
 * @param <T> тип значений, которые может содержать связный список.
 */
public class ForwardLinked<T> implements Iterable<T> {
	/**
	 * Первый элемент списка.
	 */
	private Node<T> head;
	/**
	 * Количество изменений в списке.
	 */
	private int modificationCount;
	/**
	 * Количество элементов в списке.
	 */
	private int size;

	/**
	 * Узел списка, содержащий значение.
	 * @param <V> Тип значения списка.
	 */
	private static class Node<V> {
		/**
		 * Значение узла.
		 */
		private V value;
		/**
		 * Указатель на следующий узел.
		 */
		private Node<V> next;

		/**
		 * Конструктор узла.
		 * @param value значение узла.
		 * @param next указатель на следующий узел.
		 */
		Node(final V value, final Node<V> next) {
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * Добавляет новое значение в конец списка.
	 * @param value новое значение, которое необходимо добавить в список.
	 */
	public void add(final T value) {
		Node<T> node = new Node<>(value, null);
		if (head == null) {
			head = node;
			return;
		}
		Node<T> tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}
		tail.next = node;
		modificationCount++;
		size++;
	}

	/**
	 * Добавление элемента в начало списка.
	 * @param value новое значение, которое необходимо добавить в список.
	 */
	public void addFirst(final T value) {
		head = new Node<>(value, head);
		size++;
	}

	/**
	 * Удаление головного элемента списка.
	 * @return значение головного элемента.
	 * @throws NullPointerException если список пустой.
	 */
	public T deleteFirst() {
		Objects.requireNonNull(head, "ForwardLinked is empty");
		Node<T> temp = head;
		head = temp.next;
		temp.next = null;
		modificationCount++;
		size--;
		return temp.value;
	}

	/**
	 * Получение количества элементов в списке.
	 * @return количество элементов в списке.
	 */
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	/**
	 * Итератор по связному списку.
	 */
	private class Itr implements Iterator<T> {
		/**
		 * Указатель на следующий узел.
		 */
		private Node<T> pointer = head;
		/**
		 * Ожидаемое количество изменений в списке.
		 */
		private int expectedModificationCount = modificationCount;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return pointer != null;
		}

		/**
		 * {@inheritDoc}
		 * @throws ConcurrentModificationException в случае, если список был
		 * изменён во время итерации.
		 */
		@Override
		public T next() {
			if (expectedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T result = pointer.value;
			pointer = pointer.next;
			return result;
		}
	}

	/**
	 * Получение головного значения.
	 * @return головное значение.
	 */
	public T peek() {
		return head.value;
	}
}

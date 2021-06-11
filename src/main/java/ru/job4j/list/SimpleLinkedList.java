package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Простой связный список.
 * @param <E> {@inheritDoc}
 */
public class SimpleLinkedList<E> implements List<E> {
	/**
	 * Количество изменений в связном списке.
	 */
	private int modificationCount;
	/**
	 * Первый элемент списка.
	 */
	private Node<E> first;
	/**
	 * Последний элемент списка.
	 */
	private Node<E> last;

	/**
	 * Узел списка, который содержит значение типа E.
	 * @param <V> Значение.
	 */
	private class Node<V> {

		/**
		 * Предыдущий узел.
		 */
		private Node<V> previous;
		/**
		 * Следующий узел.
		 */
		private Node<V> next;
		/**
		 * Значение элемента списка.
		 */
		private V value;

		/**
		 * Конструктор узла.
		 * @param value Значение, которое содержится в узле.
		 */
		Node(final  V value) {
			this.value =  value;
		}
	}
	/**
	 * Размер связного списка.
	 */
	private int size;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(final E value) {
		Node<E> newNode = new Node<>(value);
		if (size != 0) {
			Node<E> tempLast = last;
			newNode.previous = last;
			tempLast.next = newNode;
			last = newNode;
		} else {
			first = newNode;
			last = newNode;
		}
		size++;
		modificationCount++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(final int index) {
		Objects.checkIndex(index, size);
		Node<E> result = first;
		for (int i = 1; i <= index; i++) {
			result = result.next;
		}
		return result.value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * Итератор по связному списку.
	 */
	private class Itr implements Iterator<E> {
		/**
		 * Ожидаемое количество изменений в связном списке.
		 */
		private int expectedModificationCount = modificationCount;
		/**
		 * Курсор итератора, указывающий на следующий элемент для выбора.
		 */
		private Node<E> pointer = first;
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return pointer != null;
		}
		/**
		 * {@inheritDoc}
		 * @throws ConcurrentModificationException в случае, если во время
		 * итерации произошли изменения в связном списке.
		 */
		@Override
		public E next() {
			if (expectedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E result = pointer.value;
			pointer = pointer.next;
			return result;
		}
	}

	/**
	 * Получение первого элемент списка.
	 * @return первый элемент списка.
	 */
	public Node<E> getFirst() {
		return first;
	}

	/**
	 * Получение последнего элемент списка.
	 * @return последний элемент списка.
	 */
	public Node<E> getLast() {
		return last;
	}

	/**
	 * Получение количества элементов, содержащихся в связном списке.
	 * @return количествоо элементов связного списка.
	 */
	public int size() {
		return size;
	}
}

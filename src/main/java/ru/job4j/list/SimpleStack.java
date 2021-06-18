package ru.job4j.list;

/**
 * Простой стэк.
 * @param <T> тип значений, содержащихся в стэке.
 */
public class SimpleStack<T> {
	/**
	 * Хранилище значений стэка.
	 */
	private ForwardLinked<T> data = new ForwardLinked<>();

	/**
	 * Получение последнего добавленного значения.
	 * @return последний добавленный элемент.
	 */
	public T pop() {
		return data.deleteFirst();
	}

	/**
	 * Добавление нового значения в стэк.
	 * @param value Новое значение.
	 */
	public void push(final T value) {
		data.addFirst(value);
	}
}

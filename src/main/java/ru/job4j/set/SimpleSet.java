package ru.job4j.set;

import ru.job4j.generics.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * Простое множество.
 *
 * @param <T> тип значений, которые могут содержаться во множестве.
 */
public class SimpleSet<T> implements Set<T> {
	/**
	 * Внутреннее хранилище уникальных значений, основанное на массиве.
	 */
	private SimpleArray<T> data = new SimpleArray<>();
	/**
	 * Количество элементов во множестве.
	 */
	private int size;

	/**
	 * Добавляет значение во множество.
	 *
	 * @param value значение, которое необходимо добавить.
	 * @return {@code true} в случае, если добавляемого значения во множестве
	 * нет ещё, иначе - {@code false}.
	 */
	@Override
	public boolean add(final T value) {
		if (!contains(value)) {
			data.add(value);
			size++;
			return true;
		}
		return false;
	}

	/**
	 * Проверяет, содержится ли значение во множестве.
	 *
	 * @param value значение, о наличии которого во множестве необходимо узнать.
	 * @return {@code true} в случае, если значени во множестве содержится,
	 * иначе - {@code false}.
	 */
	@Override
	public boolean contains(final T value) {
		for (T item : data) {
			if (Objects.equals(value, item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Получение количества элементов во множестве.
	 *
	 * @return количество элементов во множестве.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return data.iterator();
	}
}

package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Проход по массиву в обратном порядке.
 */
public class BackwardArrayIterator implements Iterator<Integer> {
	/**
	 * Массив данных.
	 */
	private final int[] data;
	/**
	 * Указатель на текущий элемент массива для итератора.
	 */
	private int point;

	/**
	 * Конструктор итератора по массиву.
	 * @param data Массив целых чисел.
	 */
	public BackwardArrayIterator(final int[] data) {
		this.data = data;
		this.point = data.length - 1;
	}

	/**
	 * Проверка наличия следующего элемента в массиве.
	 * @return true в случае, если элемент следующий есть,
	 * иначе - false.
	 */
	@Override
	public boolean hasNext() {
		return point >= 0;
	}

	/**
	 * Получение следующего элемента из массива.
	 * @return Следующий элемент из массива.
	 */
	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException("Итератор вышел за пределы массива.");
		}
		return data[point--];
	}
}

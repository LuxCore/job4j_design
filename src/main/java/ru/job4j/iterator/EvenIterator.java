package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по чётным числам.
 */
public class EvenIterator implements Iterator<Integer> {
	/**
	 * Массив целых чисел.
	 */
	private int[] data;
	/**
	 * Указатель на номер элемента, с которого необходимо продолжить поиск.
	 */
	private int cursor;

	/**
	 * Конструирует итератор с передачей массива целых чисел.
	 * @param numbers Массив целых чисел.
	 */
	public EvenIterator(final int[] numbers) {
		data = numbers;
	}

	/**
	 * Проверка наличия следующего чётного числа.
	 * @return true в случае наличия следующего чётного числа, false - в
	 * случае отсутствия.
	 */
	@Override
	public boolean hasNext() {
		return nextPointer() > -1;
	}

	/**
	 * Возвращает индекс элемента массива, если в наличии есть следующее
	 * чётное число.
	 * @return Индекс элемента массива, в котором содержится чётное число. В
	 * случае отсутствия возвращается -1.
	 */
	private int nextPointer() {
		for (int i = cursor; i < data.length; i++) {
			if (data[i] % 2 == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Возвращает следующее чётное число.
	 * @throws NoSuchElementException В случае отсутствия следующего чётного
	 * числа.
	 * @return Следующее чётное число.
	 */
	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException("Чётные числа отсутствуют");
		}
		cursor = nextPointer();
		return data[cursor++];
	}
}

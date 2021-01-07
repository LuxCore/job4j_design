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
		for (; cursor < data.length; cursor++) {
			if (data[cursor] % 2 == 0) {
				return true;
			}
		}
		return false;
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
		return data[cursor++];
	}
}

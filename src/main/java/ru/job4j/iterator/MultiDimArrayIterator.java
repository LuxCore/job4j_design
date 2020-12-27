package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по многомерному массиву.
 */
public class MultiDimArrayIterator implements Iterator<Integer> {
	/**
	 * Многомерный массив целых чисел.
	 */
	private int[][] data;
	/**
	 * Указатель на номер ячейки массива 1 уровня.
	 */
	private int row = 0;
	/**
	 * Указатель на номер ячейки массива 2 уровня.
	 */
	private int col = 0;

	/**
	 * Конструктор многомерного массива.
	 * @param data Многомерный массив.
	 */
	public MultiDimArrayIterator(final int[][] data) {
		this.data = data;
	}

	/**
	 * Проверка наличия следующего элемента в многомерном массиве.
	 * @return true, если следующий элемент существует, в противном
	 * случае - false.
	 */
	@Override
	public boolean hasNext() {
		boolean result = false;
		for (int i = row, j = col; i < data.length; i++) {
			if (data[i].length > 0 && j < data[i].length) {
				result = true;
				break;
			}
			j = 0;
		}
		return result;
	}

	/**
	 * Получение элемента из многомерного массива.
	 * @return Элемент многомерного массива.
	 */
	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException("Элементы в массиве отсутствуют.");
		}
		return nextElement();
	}

	/**
	 * Рекурсия, которая возвращает элемент сразу, если он есть, и
	 * переходит к следующему внутреннему массиву, если текущий - пустой.
	 * @return Целое число внутреннего массива.
	 */
	private Integer nextElement() {
		if (0 < data[row].length && data[row].length > col) {
			return data[row][col++];
		}
		row++;
		col = 0;
		return nextElement();
	}
}

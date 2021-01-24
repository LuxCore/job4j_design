package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор итераторов.
 * @param <T> Тип элементов внутреннего итератора.
 */
public class FlatMap<T> implements Iterator<T> {
	/**
	 * Ссылка на итератор итераторов.
	 */
	private final Iterator<Iterator<T>> data;
	/**
	 * Указатель на следующий элемент.
	 */
	private Iterator<T> cursor = Collections.emptyIterator();

	/**
	 * Конструктор, принимающий итератор итераторов, а также сразу
	 * устанавливающий указатель в начальную позицию (на первый элемент).
	 * @param data Итератор итераторов.
	 */
	public FlatMap(final Iterator<Iterator<T>> data) {
		this.data = data;
	}

	/**
	 * Проверка наличия следующего элемента. Все пустые внутренние итераторы
	 * игнорируются, а указатель устанавливается на следующий непустой
	 * внутренний итератор.
	 * @return true в случае, если внутренний итератор имеет следующий элемент,
	 * false - если элемента следующиего нет.
	 */
	@Override
	public boolean hasNext() {
		while (!cursor.hasNext() && data.hasNext()) {
			cursor = data.next();
		}
		return cursor.hasNext();
	}

	/**
	 * Возвращает следующий элемент.
	 * @return Следующий элемент.
	 */
	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return cursor.next();
	}
}

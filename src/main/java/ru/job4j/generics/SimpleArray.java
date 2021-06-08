package ru.job4j.generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс, реализующий обёртку для массива.
 * @param <T> тип элементов массива.
 */
public class SimpleArray<T> implements Iterable<T> {
	/**
	 * Массив, содержащий элементы типа T.
	 */
	private Object[] data;
	/**
	 * Указатель на номер ячейки, в которую можно вставлять значения.
	 */
	private int pointer;
	/**
	 * Счётчик изменений в хранилище. Используется для итератора.
	 */
	private int modificationCount;
	/**
	 * Начальный размер массива.
	 */
	private static final int INITIAL_SIZE = 10;
	/**
	 * Пустой массив для инициализации.
 	 */
	private static final Object[] EMPTY_DATA = {};

	/**
	 * Пустой конструктор.
	 */
	public SimpleArray() {
		data = EMPTY_DATA;
	}

	/**
	 * Конструктор, принимающий массив типа T.
	 * @param size Массив типа T.
	 */
	public SimpleArray(final int size) {
		this.data = EMPTY_DATA;
		this.data = increaseStorage(size);
	}

	/**
	 * Увеличение размера внутреннего хранилища.
	 * @param size Размер, на который нужно увеличть внутреннее
	 *                        хранилище.
	 * @return Хранилище, размер которого увеличен.
	 */
	private Object[] increaseStorage(final int size) {
		Object[] temp = (Object[]) Array.newInstance(Object.class, size);
		System.arraycopy(data, 0, temp, 0, pointer);
		return temp;
	}

	/**
	 * Метод добавляет новый элемент в массив.
	 * @param element Новый элемент.
	 */
	public void add(final T element) {
		if (pointer >= data.length) {
			data = increaseStorage(data.length + INITIAL_SIZE);
		}
		data[pointer++] = element;
		modificationCount++;
	}

	/**
	 * Замена указанным элементом element элемента, находящегося по индексу
	 * index. Перед заменой проверяется входит ли указанный индекс в
	 * существующий диапазон индексов массива.
	 * @param index Индекс, по которому происходит замена элемента указанным
	 *              элементом.
	 * @param element Элемент для замещения существующего элемента.
	 * @return true, если замена произошла успешно, иначе - false.
	 * @throws IndexOutOfBoundsException Выбрасываетс исключение в случае,
	 * когда index превышает количество существующих элементов.
	 */
	public boolean set(final int index, final T element) {
		Objects.checkIndex(index, pointer);
		data[index] = element;
		modificationCount++;
		return true;
	}

	/**
	 * Удаляет элемент под индексом index. Перед удалением проверяется
	 * входит ли указанный индекс в существующий диапазон индексов массива.
	 * @param index Индекс, по которому происходит удаление элемента.
	 * @return Удалённый элемент.
	 */
	@SuppressWarnings("unchecked")
	public T remove(final int index) {
		Objects.checkIndex(index, pointer);
		T element = (T) data[index];
		System.arraycopy(data, index + 1, data, index, --pointer - index);
		data[pointer] = null;
		modificationCount++;
		return element;
	}

	/**
	 * Получение элемента по индексу. Перед получением проверяется
	 * входит ли указанный индекс в существующий диапазон индексов массива.
	 * @param index Индекс получаемого элемента.
	 * @return Элемент по индексу index.
	 */
	@SuppressWarnings("unchecked")
	public T get(final int index) {
		Objects.checkIndex(index, pointer);
		return (T) data[index];
	}

	/**
	 * Количество элементов в SimpleArray.
	 * @return Количество элементов в SimpleArray.
	 */
	int size() {
		return pointer;
	}

	/**
	 * Итератор элементов.
	 * @return Итератор элементов.
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArrIterator();
	}

	/**
	 * Класс итератора по внутреннему массиву data.
	 */
	private class ArrIterator implements Iterator<T> {
		/**
		 * Указатель на следующий элемент внутреннего массива data.
		 */
		private int itrPointer;
		/**
		 * Ожидаемое количество изменений в хранилище на время работы итератора:
		 * если во время работы итератора были произведены манипуляции с
		 * элементами, то он прекращает свою работу.
		 */
		private int expectedModificationCount = modificationCount;

		/**
		 * Проверка на наличие следующего элемента в data.
		 * @return true, если следующий элемент есть, иначе - false.
		 */
		@Override
		public boolean hasNext() {
			return itrPointer < pointer;
		}

		/**
		 * Возвращает следующий элемент массива data.
		 * @return Слудующий элемент массива data.
		 * @throws NoSuchElementException Выбрасывается исключение в случае
		 * отсутствия следующего элемента.
		 */
		@Override
		@SuppressWarnings("unchecked")
		public T next() {
			checkForModifications();
			if (!hasNext()) {
				throw new NoSuchElementException("Запрашиваемый элемент отсутствует.");
			}
			return (T) data[itrPointer++];
		}

		final void checkForModifications() {
			if (expectedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

	/**
	 * Преобразование SimpleArray в строку.
	 * @return Строчное значение, состоящее из элементов SimpleArray.
	 */
	@Override
	public String toString() {
		return Arrays.stream(data)
				.limit(pointer)
				.map(String::valueOf)
				.collect(Collectors.joining(", ", "[", "]"));
	}
}

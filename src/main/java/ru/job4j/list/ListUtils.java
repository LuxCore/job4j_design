package ru.job4j.list;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Вспомогательные методы для работы со списками.
 */
public final class ListUtils {
	/**
	 * Скрытый конструктор, т.к. класс является утилитой.
	 */
	private ListUtils() { }
	/**
	 * Добавление {@code value} перед указанным {@code index}.
	 *
	 * @param list  список, в который необходимо вставить {@code value} перед
	 *              указанным {@code index}.
	 * @param index номер позиции, перед которой необходимо
	 *              вставить {@code value}.
	 * @param value значение, которое необходимо вставить перед
	 *              указанным {@code index}.
	 * @param <T>   тип значений, которые должны храниться в списке.
	 * @throws IndexOutOfBoundsException если индекс за пределами списка.
	 */
	public static <T> void addBefore(final List<T> list, final int index, final T value) {
		Objects.checkIndex(index, list.size());
		for (ListIterator<T> i = list.listIterator();;) {
			if (i.nextIndex() == index) {
				i.add(value);
				break;
			}
			i.next();
		}
	}

	/**
	 * Добавление {@code value} после указанного {@code index}.
	 *
	 * @param list  список, в который необходимо вставить {@code value} после
	 *              указанного {@code index}.
	 * @param index номер позиции, после которой необходимо
	 *              вставить {@code value}.
	 * @param value значение, которое необходимо вставить после
	 *              указанного {@code index}.
	 * @param <T>   тип значений, которые должны храниться в списке.
	 * @throws IndexOutOfBoundsException если индекс за пределами списка.
	 */
	public static <T> void addAfter(final List<T> list, final int index, final T value) {
		Objects.checkIndex(index, list.size());
		for (ListIterator<T> i = list.listIterator();;) {
			i.next();
			if (i.previousIndex() == index) {
				i.add(value);
				break;
			}
		}
	}

	/**
	 * Удаляет все значения в списке, если они удовлетворяют условию
	 * {@code filter}.
	 *
	 * @param list   список, в котором необходимо удалить элемент
	 *               по условию {@code filter}.
	 * @param filter условие, по которому необходимо удалить значения из списка.
	 * @param <T>    тип значений, которые должны храниться в списке.
	 */
	public static <T> void removeIf(final List<T> list, final Predicate<T> filter) {
		for (ListIterator<T> i = list.listIterator(); i.hasNext();) {
			if (filter.test(i.next())) {
				i.remove();
			}
		}
	}

	/**
	 * Замещает все значения в списке значением {@code value}, если они
	 * удовлетворяют условию {@code filter}.
	 *
	 * @param list   список, в котором необходимо произвести замещение.
	 * @param filter условие, по которому необходимо произвести замещение.
	 * @param value  значение, которым необходимо заместить те значения,
	 *               которые удовлетворяют условию {@code filter}.
	 * @param <T>    тип значений, которые должны храниться в списке.
	 */
	public static <T> void replaceIf(final List<T> list, final Predicate<T> filter, final T value) {
		for (ListIterator<T> i = list.listIterator(); i.hasNext();) {
			if (filter.test(i.next())) {
				i.set(value);
			}
		}
	}

	/**
	 * Удаляет все элементы списка {@code list}, которые есть в списке
	 * {@code elements}.
	 *
	 * @param list     список, в котором необходимо удалить все элементы.
	 * @param elements список элементов, которые необходимо удалить из списка
	 *                 {@code list}.
	 * @param <T>      тип значений, которые должны храниться в списке.
	 */
	public static <T> void removeAll(final List<T> list, final List<T> elements) {
		for (ListIterator<T> li = list.listIterator(); li.hasNext();) {
			T el = li.next();
			for (T element : elements) {
				if (Objects.equals(el, element)) {
					li.remove();
					break;
				}
			}
		}
	}
}

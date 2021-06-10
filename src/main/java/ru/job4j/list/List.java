package ru.job4j.list;

/**
 * Базовый список.
 * @param <E> Элемент списка ссылочного типа.
 */
public interface List<E> extends Iterable<E> {
	/**
	 * Добавление значения в конец списка.
	 * @param value Добавляемый значение.
	 */
	void add(E value);

	/**
	 * Получение элемента из списка по индексу.
	 * @param index Индекс, по которому находится искомый элемент.
	 * @throws IndexOutOfBoundsException Будет выброшено в случае, если index не удовлетворяет
	 * условию <code>index >= 0 && index < List.size</code>.
	 * @return Искомый элемент.
	 */
	E get(int index);
}

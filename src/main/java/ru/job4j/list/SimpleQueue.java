package ru.job4j.list;

/**
 * Простая очередь.
 * @param <T> тип значений, которые могут храниться в очереди.
 */
public class SimpleQueue<T> {
	/**
	 * Стэк для входящих значений.
	 */
	private final SimpleStack<T> inbox = new SimpleStack<>();
	/**
	 * Стэк для извлекаемых значений.
	 */
	private final SimpleStack<T> outbox = new SimpleStack<>();
	/**
	 * Количество элементов в очереди.
	 */
	private int size;

	/**
	 * Помещает новое значение в очередь.
	 * @param value новое значение для добавления.
	 */
	public void enqueue(final T value) {
		inbox.push(value);
		size++;
	}

	/**
	 * Получение и удаление значения из начала очереди.
	 * @return начальное значение очереди.
	 * @throws NullPointerException если очередь пустая.
	 */
	public T dequeue() {
		moveElementsFromInToOut();
		size--;
		return outbox.pop();
	}

	/**
	 * Получение значения, которое должно быть получено методом
	 * {@link #dequeue() dequeue()}. Отличие от метода {@code dequeue}: не
	 * выбрасывает исключение.
	 * @return начальное значение очереди или {@code null}, если очередь пустая.
	 */
	public T peek() {
		moveElementsFromInToOut();
		return outbox.size() != 0 ? outbox.peek() : null;
	}

	/**
	 * Получение количества элементов в очереди.
	 * @return количество элементов в очереди.
	 */
	public int size() {
		return size;
	}

	/**
	 * Перемещение элементов из входящего стэка в исходищий.
	 */
	private void moveElementsFromInToOut() {
		if (outbox.size() == 0 && inbox.size() != 0) {
			while (inbox.size() != 0) {
				outbox.push(inbox.pop());
			}
		}
	}
}

package ru.job4j.generics.strore;

import java.util.HashMap;
import java.util.Map;

/**
 * Хранилище для сущностей, наследуемых от Base.
 * @param <T> Сущность-наследник от Base.
 */
public final class MemStore<T extends Base> implements Store<T> {
	/**
	 * Хранилище соответствий. Ключ хранит идентификатор судности.
	 */
	private final Map<String, T> mem = new HashMap<>();

	/**
	 * Добравление сущности model в хранилище.
	 * @param model Добавляемая сущность
	 */
	@Override
	public void add(final T model) {
		mem.putIfAbsent(model.getId(), model);
	}

	/**
	 * Замещение существующей сущности новой сущностью model.
	 * @param id Идентификатор заменяемой сущности.
	 * @param model Новая сущность, которая должна заменить существующую.
	 * @return true, если замещение элемента прошло успешно, иначе - false.
	 */
	@Override
	public boolean replace(final String id, final T model) {
		return mem.replace(id, mem.get(id), model);
	}

	/**
	 * Удаление сущности из хранилища.
	 * @param id Идентификатор удаляемой сущности.
	 * @return true, если удаление элемент прошло успешно, иначе - false.
	 */
	@Override
	public boolean delete(final String id) {
		boolean result = mem.containsKey(id);
		mem.remove(id);
		return result;
	}

	/**
	 * Поиск сущности по её идентификтору.
	 * @param id Идентификатор искомой сущности.
	 * @return При наличии искомого ключа id возвращается соответствующее
	 * значение.
	 */
	@Override
	public T findById(final String id) {
		return mem.get(id);
	}

	/**
	 * Количестов элементов в хранилище.
	 * @return Количество элементов в хранилилще.
	 */
	@Override
	public int size() {
		return mem.size();
	}
}

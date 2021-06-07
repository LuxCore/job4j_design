package ru.job4j.generics.strore;

/**
 * Хранилище ролей.
 */
public class RoleStore implements Store<Role> {
	/**
	 * Хранилище ролей.
	 */
	private final Store<Role> roles = new MemStore<>();

	/**
	 * Добавление роли в хранилище.
	 * @param model Добавляемая роль.
	 */
	@Override
	public void add(final Role model) {
		roles.add(model);
	}

	/**
	 * Замещение существующей роли новой.
	 * @param id Идентификатор существующей роли.
	 * @param model Новая роль, которая должна заместить существующую.
	 * @return true, если замащение роли прошло успешно, иначе - false.
	 */
	@Override
	public boolean replace(final String id, final Role model) {
		return roles.replace(id, model);
	}

	/**
	 * Удаление роли.
	 * @param id Идентификатор удаляемой роли.
	 * @return true, если удаление роли прошло успешно, иначе - false.
	 */
	@Override
	public boolean delete(final String id) {
		return roles.delete(id);
	}

	/**
	 * Поиск роли по её идентификатору.
	 * @param id Идентификатор искомой роли.
	 * @return Искомая роль, если ключ id существует в хранилище, инаече
	 * возвращается null.
	 */
	@Override
	public Role findById(final String id) {
		return roles.findById(id);
	}

	/**
	 * Количество ролей в хранилище.
	 * @return Количество ролей в хранилище.
	 */
	@Override
	public int size() {
		return roles.size();
	}
}

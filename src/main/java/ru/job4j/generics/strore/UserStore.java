package ru.job4j.generics.strore;

/**
 * Хранилище пользователей.
 */
public class UserStore implements Store<User> {
	/**
	 * Хранилище пользователей.
	 */
	private final Store<User> users = new MemStore<>();

	/**
	 * Добавление пользователя в хранилище.
	 * @param model Добавляемый пользователь.
	 */
	@Override
	public void add(final User model) {
		users.add(model);
	}

	/**
	 * Замещение существующего пользователя новым.
	 * @param id Идентификатор существующего пользователя.
	 * @param model Новый пользователь, который должен заменить существующего.
	 * @return true, если замещение пользователя прошло успешно, иначе - false.
	 */
	@Override
	public boolean replace(final String id, final User model) {
		return users.replace(id, model);
	}

	/**
	 * Удаление пользователя из хранилища.
	 * @param id Идентификтор удаляемого пользователя.
	 * @return true, если удаление пользователя прошло успешно, иначе - false.
	 */
	@Override
	public boolean delete(final String id) {
		return users.delete(id);
	}

	/**
	 * Поиск пользователя по его идентификатору.
	 * @param id Идентификатор искомого пользователя.
	 * @return Искомый пользователь при наличии ключа id в хранилище. Иначе
	 * возвращается null.
	 */
	@Override
	public User findById(final String id) {
		return users.findById(id);
	}

	/**
	 * Количество пользователей в хранилище.
	 * @return Количество пользователей в хранилище.
	 */
	@Override
	public int size() {
		return users.size();
	}
}

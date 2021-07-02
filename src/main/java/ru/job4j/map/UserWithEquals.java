package ru.job4j.map;

import java.time.LocalDate;

/**
 * Пользователь с переопределённым методом {@code equals()}.
 */
public class UserWithEquals extends User {
	/**
	 * Создание пользователя с указанием всех значений.
	 *
	 * @param name     начальное имя пользователя.
	 * @param children количество детей.
	 * @param birthday день рождения пользователя.
	 */
	public UserWithEquals(final String name, final int children, final LocalDate birthday) {
		super(name, children, birthday);
	}

	/**
	 * Сравнение текущего пользователя с пользователем {@code o}.
	 *
	 * @param o пользователь, с которым нужно сравнить текущего.
	 * @return {@code true}, если объекты равны по содержимому,
	 * иначе - {@code false}.
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserWithEquals user = (UserWithEquals) o;
		return this.getChildren() == user.getChildren()
				&& this.getName().equals(user.getName())
				&& this.getBirthday().equals(user.getBirthday());
	}
}

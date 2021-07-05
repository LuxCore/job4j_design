package ru.job4j.map;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Пользователь с переопределёнными методами {@code equals()} и {@code hashCode()}.
 */
public class UserWithEqualsHashcode extends User {
	/**
	 * Создание пользователя с указанием всех значений.
	 *
	 * @param name     начальное имя пользователя.
	 * @param children количество детей.
	 * @param birthday день рождения пользователя.
	 */
	public UserWithEqualsHashcode(final String name, final int children, final LocalDate birthday) {
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
		UserWithEqualsHashcode user = (UserWithEqualsHashcode) o;
		return this.getChildren() == user.getChildren()
				&& this.getName().equals(user.getName())
				&& this.getBirthday().equals(user.getBirthday());
	}

	/**
	 * Вычисление хэш кода объекта.
	 *
	 * @return hash code.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getChildren(), this.getBirthday());
	}
}

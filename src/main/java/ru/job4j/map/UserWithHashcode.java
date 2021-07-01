package ru.job4j.map;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Пользователь с переопределённым методом {@code hashCode()}.
 */
public class UserWithHashcode extends User {
	/**
	 * Создание пользователя с указанием всех значений.
	 *
	 * @param name     начальное имя пользователя.
	 * @param children количество детей.
	 * @param birthday день рождения пользователя.
	 */
	public UserWithHashcode(final String name, final int children, final LocalDate birthday) {
		super(name, children, birthday);
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

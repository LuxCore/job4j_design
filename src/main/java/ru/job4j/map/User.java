package ru.job4j.map;

import java.time.LocalDate;

/**
 * Пользователь или просто человек.
 */
public class User {
	/**
	 * Имя пользователя.
	 */
	private String name;
	/**
	 * Количество детей пользователя.
	 */
	private int children;
	/**
	 * День рождения пользователя.
	 */
	private LocalDate birthday;

	/**
	 * Получение имени пользователя.
	 * @return Имя пользователя.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Установка имени пользователя.
	 * @param name имя пользователя.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Получение количества детей пользователя.
	 * @return количество детей пользователя.
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * Установка количества детей пользователя.
	 * @param children количество детей пользователя.
	 */
	public void setChildren(final int children) {
		this.children = children;
	}

	/**
	 * Получение дня рождения пользователя.
	 * @return день рождения пользователя
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * Установка дня рождения пользователя.
	 * @param birthday день рождения пользователя.
	 */
	public void setBirthday(final LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * Конструктор по умолчанию.
	 */
	public User() { }

	/**
	 * Создание пользователя с указанием всех значений.
	 * @param name начальное имя пользователя.
	 * @param children количество детей.
	 * @param birthday день рождения пользователя.
	 */
	public User(final String name, final int children, final LocalDate birthday) {
		this.name = name;
		this.children = children;
		this.birthday = birthday;
	}

	/**
	 * Строковое представление пользователя.
	 * @return строковое представление пользователя.
	 */
	@Override
	public String toString() {
		return "User{"
				+ "name='" + name + '\''
				+ "children='" + children + '\''
				+ "birthday='" + birthday + '\''
				+ '}';
	}
}

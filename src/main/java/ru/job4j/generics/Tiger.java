package ru.job4j.generics;

/**
 * Класс тигра.
 */
public class Tiger extends Predator {
	/**
	 * Счётчик для тигра.
	 */
	private static int id;

	static {
		id++;
	}

	/**
	 * Вывод на печать сущности тигра.
	 * @return Сущность тигра.
	 */
	@Override
	public String toString() {
		return "Tiger_" + id;
	}
}

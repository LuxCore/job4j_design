package ru.job4j.generics;

/**
 * Класс животного.
 */
public class Animal {
	/**
	 * Счётчик для животного.
	 */
	private static int id;

	static {
		id++;
	}

	/**
	 * Вывод на печать сущности животного.
	 * @return Сущность животного.
	 */
	@Override
	public String toString() {
		return "Animal_" + id;
	}
}

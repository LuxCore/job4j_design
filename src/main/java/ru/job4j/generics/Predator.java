package ru.job4j.generics;

/**
 * Класс хищника.
 */
public class Predator extends Animal {
	/**
	 * Счётчик для хищника.
	 */
	private static int id;

	static {
		id++;
	}

	/**
	 * Вывод на печать сущности хищника.
	 * @return Сущность хищника.
	 */
	@Override
	public String toString() {
		return "Predator_" + id;
	}
}

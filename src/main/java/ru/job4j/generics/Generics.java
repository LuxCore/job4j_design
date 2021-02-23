package ru.job4j.generics;

import java.util.List;

/**
 * Класс с утилитами для проверки обобщений.
 */
public class Generics {
	/**
	 * Метод печати списка с любым содержимым.
	 * @param list Список с любым содержимым.
	 */
	public void printObject(final List<?> list) {
		for (Object next : list) {
			System.out.println("Текущий элемент: " + next);
		}
	}
	/**
	 * Метод печати списка, который содержит все подклассы хищника.
	 * @param list Список, который содержит все подклассы хищника.
	 */
	public void printBoundedWildCard(final List<? extends Predator> list) {
		for (Object next : list) {
			System.out.println("Текущий элемент: " + next);
		}
	}
	/**
	 * Метод печати списка, который содержит все надклассы хищника.
	 * @param list Список, который содержит все надклассы хищника.
	 */
	public void printLowerBoundedWildCard(final List<? super Predator> list) {
		for (Object next : list) {
			System.out.println("Текущий элемент: " + next);
		}
	}
}

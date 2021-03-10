package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс с утилитами для проверки обобщений.
 */
public class Generics {
	/**
	 * Проверка обощений.
	 * @param args Аргументы командной строки.
	 */
	public static void main(final String[] args) {
		Generics gen = new Generics();
		List<Animal> first = new ArrayList<>();
		List<Predator> second = new ArrayList<>();
		List<Tiger> third = new ArrayList<>();
		first.add(new Animal());
		second.add(new Predator());
		third.add(new Tiger());

		gen.printObject(first);
		gen.printObject(second);
		gen.printObject(third);
		System.out.println();

//		gen.printBoundedWildCard(first);
		gen.printBoundedWildCard(second);
		gen.printBoundedWildCard(third);
		System.out.println();

		gen.printLowerBoundedWildCard(first);
		gen.printLowerBoundedWildCard(second);
//		gen.printLowerBoundedWildCard(third);
	}

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

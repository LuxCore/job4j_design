package ru.job4j.generics;

public class Predator extends Animal {
	private static int id;

	static {
		id++;
	}

	@Override
	public String toString() {
		return "Predator_" + id;
	}
}

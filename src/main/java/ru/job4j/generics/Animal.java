package ru.job4j.generics;

public class Animal {
	private static int id;

	static {
		id++;
	}

	@Override
	public String toString() {
		return "Animal_" + id;
	}
}

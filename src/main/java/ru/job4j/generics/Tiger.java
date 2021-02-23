package ru.job4j.generics;

public class Tiger extends Predator {
	private static int id;

	static {
		id++;
	}

	@Override
	public String toString() {
		return "Tiger_" + id;
	}
}

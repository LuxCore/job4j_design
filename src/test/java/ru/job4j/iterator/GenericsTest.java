package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.generics.Animal;
import ru.job4j.generics.Generics;
import ru.job4j.generics.Predator;
import ru.job4j.generics.Tiger;

import java.util.ArrayList;
import java.util.List;

class GenericsTest {
	private Generics generics;
	private List<Animal> first;
	private List<Predator> second;
	private List<Tiger> third;

	@BeforeEach
	void createNewGenerics() {
		generics = new Generics();
		first = new ArrayList<>();
		second = new ArrayList<>();
		third = new ArrayList<>();
		first.add(new Animal());
		second.add(new Predator());
		third.add(new Tiger());
	}

	@Test
	void testWildcard() {
		generics.printObject(first);
		generics.printObject(second);
		generics.printObject(third);
	}

	@Test
	void testUpperBoundedWildcard() {
		generics.printBoundedWildCard(second);
		generics.printBoundedWildCard(third);
	}

	@Test
	void testLowerBoundedWildcard() {
		generics.printLowerBoundedWildCard(first);
		generics.printLowerBoundedWildCard(second);
	}
}

package ru.job4j.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
	void stubTestForMainMethod() {
		PrintStream sysOut = System.out;

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final String utf8 = StandardCharsets.UTF_8.name();
		try {
			PrintStream ps = new PrintStream(baos, true, utf8);
			System.setOut(ps);
			String newLine = System.getProperty("line.separator");
			String expected = "Текущий элемент: Animal_1" + newLine
					+ "Текущий элемент: Predator_1" + newLine
					+ "Текущий элемент: Tiger_1" + newLine + newLine
					+ "Текущий элемент: Predator_1" + newLine
					+"Текущий элемент: Tiger_1" + newLine + newLine
					+ "Текущий элемент: Animal_1" + newLine
					+ "Текущий элемент: Predator_1" + newLine;
			Generics.main(new String[]{});
			String actual = baos.toString(utf8);

			assertEquals(expected, actual);

			System.setOut(sysOut);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
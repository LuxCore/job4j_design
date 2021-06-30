package ru.job4j.map;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MapTest {

	@Test
	void checkGettersAndSettersOfUser() {
		User user = new User();
		user.setName("Denis.Kitrish");
		user.setChildren(3);
		user.setBirthday(LocalDate.of(1983, Month.DECEMBER, 15));
		assertAll(
				() -> assertEquals("Denis.Kitrish", user.getName()),
				() -> assertEquals(3, user.getChildren()),
				() -> assertEquals("1983-12-15", user.getBirthday().toString()),
				() -> assertEquals("User{name='Denis.Kitrish',children='3',birthday='1983-12-15'}",
						user.toString())
		);
	}

	@Test
	void checkUserConstructor() {
		User user = new User("Denis.Kitrish", 3,
				LocalDate.of(1983, Month.DECEMBER, 15));
		assertAll(
				() -> assertEquals("Denis.Kitrish", user.getName()),
				() -> assertEquals(3, user.getChildren()),
				() -> assertEquals("1983-12-15", user.getBirthday().toString()),
				() -> assertEquals("User{name='Denis.Kitrish',children='3',birthday='1983-12-15'}",
						user.toString())
		);
	}
}
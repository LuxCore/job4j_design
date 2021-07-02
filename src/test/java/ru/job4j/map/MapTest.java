package ru.job4j.map;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

	@Test
	void whenObjectHasNoEqualsAndHashcode() {
		User johnDoe = new User("John Doe", 2, LocalDate.of(1970, 1, 1));
		User johnDoeTheSame = new User("John Doe", 2, LocalDate.of(1970, 1, 1));
		Map<User, Object> users = Map.of(johnDoe, new Object(), johnDoeTheSame, new Object());
		assertAll(
				() -> assertNotEquals(johnDoe, johnDoeTheSame,
						"johnDoe does not equal johnDoeTheSame"),
				() -> assertNotEquals(johnDoe.hashCode(), johnDoeTheSame.hashCode(),
						"johnDoe.hashCode() and johnDoeTheSame.hashCode()"),
				() -> assertEquals(2, users.size()),
				() -> assertFalse(users.containsKey(new User("John Doe", 2, LocalDate.of(1970, 1, 1))))
		);
	}

	@Test
	void whenObjectHasNoEqualsAndHasHashcode() {
		UserWithHashcode johnDoe = new UserWithHashcode(
				"John Doe", 2, LocalDate.of(1970, 1, 1));
		UserWithHashcode johnDoeTheSame = new UserWithHashcode(
				"John Doe", 2, LocalDate.of(1970, 1, 1));
		Map<UserWithHashcode, Object> users = Map.of(johnDoe, new Object(), johnDoeTheSame, new Object());
		UserWithHashcode expected = new UserWithHashcode(
				"John Doe", 2, LocalDate.of(1970, 1, 1));
		assertAll(
				() -> assertNotEquals(johnDoe, johnDoeTheSame,
						"johnDoe does not equal johnDoeTheSame"),
				() -> assertEquals(johnDoe.hashCode(), johnDoeTheSame.hashCode(),
						"johnDoe.hashCode() and johnDoeTheSame.hashCode()"),
				() -> assertEquals(2, users.size(), "users.size()"),
				() -> assertFalse(users.containsKey(expected),
						"users.containsKey(" + expected.toString() + ")")
		);
	}

	@Test
	void whenObjectHasEqualsAndHasNoHashcode() {
		UserWithEquals johnDoe = new UserWithEquals("John Doe", 2, LocalDate.of(1970, 1, 1));
		UserWithEquals johnDoeTheSame = new UserWithEquals("John Doe", 2, LocalDate.of(1970, 1, 1));
		Map<UserWithEquals, Object> users = new HashMap<>();
		users.put(johnDoe, new Object());
		users.put(johnDoeTheSame, new Object());
		UserWithEquals expected = new UserWithEquals(
				"John Doe", 2, LocalDate.of(1970, 1, 1));
		assertAll(
				() -> assertEquals(johnDoe, johnDoeTheSame, "johnDoe equals johnDoeTheSame"),
				() -> assertNotEquals(johnDoe.hashCode(), johnDoeTheSame.hashCode(),
						"johnDoe.hashCode() and johnDoeTheSame.hashCode()"),
				() -> assertEquals(2, users.size(), "users.size()"),
				() -> assertFalse(users.containsKey(expected),
						"users.containsKey(" + expected.toString() + ")")
		);
	}
}
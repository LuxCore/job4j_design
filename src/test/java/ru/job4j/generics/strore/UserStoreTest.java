package ru.job4j.generics.strore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserStoreTest {
	private Store<User> users;

	@BeforeEach
	public void setup() {
		users = new UserStore();
	}

	@Test
	void add() {
		users.add(new User("User1"));
		users.add(new User("User1"));
		users.add(new User("User2"));
		User user3 = new User("User3");
		users.add(user3);
		users.add(new User(null));
		assertAll(
				() -> assertEquals("User1", users.findById("User1").getId()),
				() -> assertEquals("User2", users.findById("User2").getId()),
				() -> assertEquals(user3, users.findById("User3")),
				() -> assertNotEquals(new User("User4"), users.findById(null)),
				() -> assertNotNull(users.findById("User1")),
				() -> assertNull(users.findById(null).getId()),
				() -> assertNotEquals(new Role("Role"), users.findById("User1")),
				() -> assertEquals(4, users.size())
		);
	}

	@Test
	@DisplayName("should throw NullPointerException")
	void whenAddNull() {
		assertThrows(NullPointerException.class, () -> users.add(null));
	}

	@Test
	@DisplayName("should replace successfully with non-null user")
	void replace() {
		users.add(new User("User1"));
		User user2 = new User("User2");
		users.add(user2);
		assertAll(
				() -> assertTrue(users.replace("User2", new User("User22"))),
				() -> assertNull(users.findById("User22")),
				() -> assertEquals(new User("User22"), users.findById("User2")),
				() -> assertNull(users.findById("User1")),
				() -> assertNotEquals(user2, users.findById("User1")),
				() -> assertNull(users.findById("User3")),
				() -> assertEquals(new User("User22").hashCode(), users.findById("User2").hashCode())
		);
	}

	@Test
	@DisplayName("should replace successfully with null user or key")
	void whenReplaceWithNulls() {
		users.add(new User("User1"));
		users.add(new User(null));
		users.add(new User("User3"));
		assertAll(
				() -> assertTrue(users.replace(null, new User("User2"))),
				() -> assertEquals(new User("User2"), users.findById(null)),
				() -> assertTrue(users.replace("User1", null)),
				() -> assertNull(users.findById("User1")),
				() -> assertTrue(users.replace(null, null)),
				() -> assertNull(users.findById(null)),
				() -> assertEquals(3, users.size())
		);
	}

	@Test
	void delete() {
		users.add(new User("User1"));
		users.add(new User(null));
		users.add(new User("User3"));
		assertAll(
				() -> assertTrue(users.delete("User1")),
				() -> assertTrue(users.delete(null)),
				() -> assertEquals(1, users.size()),
				() -> assertEquals("User3", users.findById("User3").getId()),
				() -> assertNull(users.findById("User1")),
				() -> assertNull(users.findById(null))
		);
	}
}
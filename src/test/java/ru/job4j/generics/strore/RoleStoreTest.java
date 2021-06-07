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

/**
 *
 */
class RoleStoreTest {
	private Store<Role> roles;

	@BeforeEach
	public void setup() {
		roles = new RoleStore();
	}

	@Test
	void add() {
		roles.add(new Role("Role1"));
		roles.add(new Role("Role1"));
		roles.add(new Role("Role2"));
		Role role3 = new Role("Role3");
		roles.add(role3);
		roles.add(new Role(null));
		assertAll(
				() -> assertEquals("Role1", roles.findById("Role1").getId()),
				() -> assertEquals("Role2", roles.findById("Role2").getId()),
				() -> assertEquals(role3, roles.findById("Role3")),
				() -> assertNotEquals(new Role("Role4"), roles.findById(null)),
				() -> assertNotNull(roles.findById("Role1")),
				() -> assertNull(roles.findById(null).getId()),
				() -> assertNotEquals(new Role("User"), roles.findById("Role1")),
				() -> assertEquals(4, roles.size())
		);
	}

	@Test
	@DisplayName("should throw NullPointerException")
	void whenAddNull() {
		assertThrows(NullPointerException.class, () -> roles.add(null));
	}

	@Test
	@DisplayName("should replace successfully with non-null role")
	void replace() {
		roles.add(new Role("Role1"));
		Role role2 = new Role("Role2");
		roles.add(role2);
		assertAll(
				() -> assertTrue(roles.replace("Role2", new Role("Role22"))),
				() -> assertNull(roles.findById("Role22")),
				() -> assertEquals(new Role("Role22"), roles.findById("Role2")),
				() -> assertTrue(roles.replace("Role1", null)),
				() -> assertNull(roles.findById("Role1")),
				() -> assertNotEquals(role2, roles.findById("Role1")),
				() -> assertNull(roles.findById("Role3")),
				() -> assertEquals(new User("Role22").hashCode(), roles.findById("Role2").hashCode())
		);
	}

	@Test
	@DisplayName("should replace successfully with null role or key")
	void whenReplaceWithNulls() {
		roles.add(new Role("Role1"));
		roles.add(new Role(null));
		roles.add(new Role("Role3"));
		assertAll(
				() -> assertTrue(roles.replace(null, new Role("Role2"))),
				() -> assertEquals(new Role("Role2"), roles.findById(null)),
				() -> assertTrue(roles.replace("Role1", null)),
				() -> assertNull(roles.findById("Role1")),
				() -> assertTrue(roles.replace(null, null)),
				() -> assertNull(roles.findById(null)),
				() -> assertEquals(3, roles.size())
		);
	}

	@Test
	void delete() {
		roles.add(new Role("Role1"));
		roles.add(new Role(null));
		roles.add(new Role("Role3"));
		assertAll(
				() -> assertTrue(roles.delete("Role1")),
				() -> assertTrue(roles.delete(null)),
				() -> assertEquals(1, roles.size()),
				() -> assertEquals("Role3", roles.findById("Role3").getId()),
				() -> assertNull(roles.findById("Role1")),
				() -> assertNull(roles.findById(null))
		);
	}
}
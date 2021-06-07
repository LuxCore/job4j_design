package ru.job4j.generics.strore;

import java.util.Objects;

/**
 * Базовый класс для сущностей, хранилищем для которых служит Store.
 */
public abstract class Base {
	/**
	 * Идентификатор сущности.
	 */
	private final String id;

	/**
	 * Конструктор сущности.
	 * @param id Идентификатор сущности.
	 */
	public Base(final String id) {
		this.id = id;
	}

	/**
	 * Получение идентификатора сущности.
	 * @return Идентификатор сущности.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Метод сравнения сущностей.
	 * @param o Сравниваемый объект с текушим.
	 * @return true если объекты равны, иначе - false.
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Base base = (Base) o;
		return id.equals(base.id);
	}

	/**
	 * Хэшкод текщей сущности.
	 * @return hash code объекта Base.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

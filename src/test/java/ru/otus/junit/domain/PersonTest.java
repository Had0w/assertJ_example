package ru.otus.junit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Class Person")
class PersonTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Person person = new Person(42, "Ivan");

        assertThat(person.getName()).isEqualTo("Ivan");
        assertThat(person.getAge()).isEqualTo(42);
    }

    @DisplayName("должен корректно назначать имя")
    @Test
    void shouldSetCorrectName() {
        Person person = new Person(42, "Ivan");
        person.setName("Tom");

        assertThat(person.getName()).isEqualTo("Tom");
    }

    @DisplayName("должен корректно назначать возраст")
    @Test
    void shouldSetCorrectAge() {
        Person person = new Person(42, "Ivan");
        person.setAge(24);

        assertThat(person.getAge()).isEqualTo(24);
    }

    @DisplayName("должен увеличивать возраст при вызове birthDay")
    @Test
    void shodIncreaseCorrectBirthDay() {
        Person person = new Person(42, "Ivan");
        person.birthDay();

        assertThat(person.getAge()).isEqualTo(43);
    }

    @DisplayName("проверить все")
    @Test
    void shouldGetNameAndAge() {
        Person person = new Person(20, "John");
        assertAll("person",
                () -> assertEquals("John", person.getName()),
                () -> assertEquals(20, person.getAge()));
    }
}

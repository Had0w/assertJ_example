package ru.otus.junit.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.junit.domain.Person;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Class PersonDaoImplTest")
class PersonDaoImplTest {
    PersonDaoImpl personDao = new PersonDaoImpl();

    @BeforeEach
    public void init() {
        PersonDaoImpl.personList = new ArrayList<>();
        PersonDaoImpl.personList.add(new Person(27, "Tom"));
        PersonDaoImpl.personList.add(new Person(25, "Andrew"));
    }

    @Test
    @DisplayName("should throw Exception if list doesn't contain person")
    void getByNameShouldThrowsExceptionTest() {
        assertThatThrownBy(() -> personDao.getByName(" ")).isInstanceOf(PersonNotFoundException.class);
    }

    @Test
    @DisplayName("should contain person with name Tom")
    void getByNameTest() {
        Person person = personDao.getByName("Tom");

        assertAll("person",
                () -> assertEquals("Tom", person.getName()),
                () -> assertEquals(27, person.getAge()));
    }

    @Test
    @DisplayName("should return list of person with size 2")
    void getAllTest() {
        personDao = new PersonDaoImpl();
        List<Person> personList = personDao.getAll();

        assertThat(personList).size().isEqualTo(2);
    }

    @Test
    @DisplayName("should delete person with name Tom")
    void deleteByNameTest() {
        var person = personDao.getByName("Tom");
        personDao.deleteByName("Tom");
        List<Person> personList = personDao.getAll();

        assertThat(personList).asList().doesNotContain(person);
    }

    @Test
    @DisplayName("should throw Exception if list doesn't contain person")
    void deleteByNameShouldThrowExceptionTest() {
        assertThatThrownBy(() -> personDao.deleteByName(" ")).isInstanceOf(PersonNotFoundException.class);
    }

    @Test
    @DisplayName("should save new person to the list")
    void saveIfPersonDoesntExistTest() {
        var person = new Person(40, "Rick");
        personDao.save(person);
        assertThat(PersonDaoImpl.personList).asList().contains(person);
    }

    @Test
    @DisplayName("should update existing person")
    void saveIsPersonAlreadyExist() {
        var newPerson = new Person(10, "Tom");
        var oldPerson = personDao.getByName("Tom");
        personDao.save(newPerson);
        assertThat(PersonDaoImpl.personList).asList().contains(newPerson).doesNotContain(oldPerson);
    }
}
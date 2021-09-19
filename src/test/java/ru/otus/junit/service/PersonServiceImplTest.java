package ru.otus.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.junit.dao.PersonDao;
import ru.otus.junit.domain.Person;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing PersonServiceImpl")
class PersonServiceImplTest {

    @Mock
    private PersonDao personDao;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl(personDao);
    }

    @Test
    @DisplayName("get personByName")
    void getByNameTest() {
        given(personDao.getByName(eq("Ivan")))
                .willReturn(new Person(10, "Ivan"));
        assertThat(personService.getByName("Ivan"))
                .isEqualTo(new Person(10, "Ivan"));
    }

    @Test
    @DisplayName("get all person")
    void getAllTest() {
        given(personDao.getAll()).willReturn(List.of(new Person(10, "Ivan"), new Person(20, "Petr")));

        assertThat(personService.getAll()).asList().contains(new Person(10, "Ivan"), new Person(20, "Petr"));
    }

    @Test
    @DisplayName("should exist with name")
    void existsWithNameTest() {
        given(personDao.getByName(eq("Ivan"))).willReturn(new Person(10, "Ivan"));

        assertThat(personService.existsWithName("Ivan")).isTrue();
    }

    @Test
    @DisplayName("should save Person")
    void saveTest() {
        var person = new Person(10, "Rick");
        personService.save(person);
        verify(personDao).save(person);
    }
}

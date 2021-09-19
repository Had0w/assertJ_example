package ru.otus.junit.service;

import ru.otus.junit.dao.PersonDao;
import ru.otus.junit.dao.PersonNotFoundException;
import ru.otus.junit.domain.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public Person getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<Person> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean existsWithName(String name) {
        try {
            var person = dao.getByName(name);
            return true;
        } catch (PersonNotFoundException ex) {
         return false;
        }
    }

    @Override
    public void save(Person p) {
       dao.save(p);
    }
}

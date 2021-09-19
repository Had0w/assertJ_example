package ru.otus.junit.dao;

import ru.otus.junit.domain.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {

    public static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person(25, "Andrew"));
        personList.add(new Person(27, "Tom"));
    }// здесь будет поле - список Person-ов

    @Override
    public Person getByName(String name) throws PersonNotFoundException {
        Optional<Person> person = personList.stream().filter(p -> p.getName().equals(name)).findFirst();
        return person.orElseThrow(() -> new PersonNotFoundException(name));
    }

    @Override
    public List<Person> getAll() {
        return PersonDaoImpl.personList;
    }

    @Override
    public void deleteByName(String name) throws PersonNotFoundException {
         var person = personList.stream().filter(p -> p.getName().equals(name)).findFirst()
                 .orElseThrow(() -> new PersonNotFoundException(name));
         personList.remove(person);
    }

    @Override
    public void save(Person person) {
        try {
        var newPerson = getByName(person.getName());
        personList.remove(newPerson);
        personList.add(person);
        } catch (PersonNotFoundException ex) {
            personList.add(person);
        }
    }
}

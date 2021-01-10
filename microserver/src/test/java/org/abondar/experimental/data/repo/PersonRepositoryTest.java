package org.abondar.experimental.data.repo;

import io.micronaut.context.BeanContext;
import io.micronaut.data.annotation.Query;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.abondar.experimental.data.model.Person;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@MicronautTest
public class PersonRepositoryTest {

    @Inject
    PersonRepository repository;


    @Test
    public void personSaveTest(){
        var person = new Person("test","test","0000-0000");

        repository.save(person);
        assertNotNull(person.getId());
    }

    @Test
    public void personFindTest(){
        var person = new Person("test","test","0000-0000");

        repository.save(person);

        var res = repository.findById(person.getId());

        assertFalse(res.isEmpty());
        assertEquals(person.getFirstName(),res.get().getFirstName());
    }

    @Test
    public void personFindPhoneTest(){
        var person = new Person("test","test","0000-0000");

        repository.save(person);

        var res = repository.findPhoneById(person.getId());

        assertEquals(person.getPhone(),res);
    }

    @Test
    public void personFindNameTest(){
        var person = new Person("test","test","0000-0000");

        repository.save(person);

        var res = repository.findOne(person.getId());

        assertEquals(person.getFirstName(),res.getFirstName());
        assertEquals(person.getLastName(),res.getLastName());

    }

    @Test
    public void personDeleteTest(){
        var person = new Person("test","test","0000-0000");

        repository.save(person);

        repository.deleteById(person.getId());
        var res =  repository.findById(person.getId());

        assertTrue(res.isEmpty());
    }
}

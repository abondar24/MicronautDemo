package org.abondar.experimental.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import org.abondar.experimental.data.model.Person;

import javax.annotation.security.PermitAll;

@Controller("/person")
@PermitAll
public class PersonController {

    @Post(processes = MediaType.APPLICATION_JSON)
    public Person create(@Body Person person){


        return person;
    }

    @Get("/{id}")
    public Person find(@PathVariable(name = "id") long id){


        return null;
    }

    @Delete("/{id}")
    public Boolean delete(@PathVariable(name = "id") long id){


        return true;
    }


}

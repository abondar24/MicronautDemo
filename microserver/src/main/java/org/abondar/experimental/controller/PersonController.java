package org.abondar.experimental.controller;

import io.micronaut.data.exceptions.EmptyResultException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import org.abondar.experimental.data.model.Person;
import org.abondar.experimental.data.repo.PersonRepository;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;



@Controller("/person")
@PermitAll
public class PersonController {

    @Inject
    PersonRepository repository;

    @Post(processes = MediaType.APPLICATION_JSON)
    public HttpResponse<Person> create(@Body Person person){

        repository.save(person);

        return HttpResponse.created(person);
    }

    @Get(value = "/{id}",produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Person> find(@PathVariable(name = "id") long id){

        var res =  repository.find(id);

        return  HttpResponse.status(HttpStatus.FOUND).body(res);
    }

    @Get(value = "/{id}/number",produces = MediaType.APPLICATION_JSON)
    @PermitAll
    public HttpResponse<String> findNumber(@PathVariable(name = "id") long id){

        var res = repository.findPhoneById(id);
        return HttpResponse.status(HttpStatus.FOUND).body(res);
    }

    @Delete(value = "/{id}",produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> delete(@PathVariable(name = "id") long id){

        repository.deleteById(id);
        return HttpResponse.ok();
    }

    @Error(exception = EmptyResultException.class)
    public HttpResponse<Person> error(){
        return HttpResponse.notFound();
    }


}

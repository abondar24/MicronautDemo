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
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.abondar.experimental.data.model.Person;
import org.abondar.experimental.data.repo.PersonRepository;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;



@Controller("/person")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class PersonController {

    @Inject
    PersonRepository repository;

    @Post(processes = MediaType.APPLICATION_JSON,produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Create person", description = "Create a new person")
    @ApiResponse(
            content = @Content(mediaType = "application/json", schema = @Schema(type = "person"))
    )
    @ApiResponse(responseCode = "201",description = "Person Created")
    public HttpResponse<Person> create(@RequestBody(description = "person data") @Body Person person){

        repository.save(person);

        return HttpResponse.created(person);
    }

    @Get(value = "/{id}",produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Find person", description = "Find person by id")
    @ApiResponse(
            content = @Content(mediaType = "application/json", schema = @Schema(type = "person"))
    )
    @ApiResponse(responseCode = "302",description = "Person found")
    @ApiResponse(responseCode = "404",description = "Person not found")
    public HttpResponse<Person> find(@Parameter(description = "person id") @PathVariable(name = "id") long id){

        var res =  repository.find(id);

        return  HttpResponse.status(HttpStatus.FOUND).body(res);
    }

    @Get(value = "/{id}/number",produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Find person", description = "Find person phone number by id")
    @ApiResponse(
            content = @Content(mediaType = "application/json", schema = @Schema(type = "person"))
    )
    @ApiResponse(responseCode = "302",description = "Person phone found")
    @ApiResponse(responseCode = "404",description = "Person not found")
    public HttpResponse<String> findNumber(@Parameter(description = "person id") @PathVariable(name = "id") long id){

        var res = repository.findPhoneById(id);
        return HttpResponse.status(HttpStatus.FOUND).body(res);
    }

    @Delete(value = "/{id}",produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete person", description = "Delete person by id")
    @ApiResponse(
            content = @Content(mediaType = "application/json", schema = @Schema(type = "person"))
    )
    @ApiResponse(responseCode = "200",description = "Person deleted")
    @ApiResponse(responseCode = "404",description = "Person not found")
    public HttpResponse<?> delete(@Parameter(description = "person id") @PathVariable(name = "id") long id){

        repository.deleteById(id);
        return HttpResponse.ok();
    }

    @Error(exception = EmptyResultException.class)
    public HttpResponse<Person> error(){
        return HttpResponse.notFound();
    }


}

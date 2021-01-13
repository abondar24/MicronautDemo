package org.abondar.experimental.controller;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.abondar.experimental.data.model.Person;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@Property(name = "micronaut.security.enabled", value = "false")
public class PersonControllerTest {

    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testSavePerson() {
        var person = new Person("test", "test", "0000-0000");
        HttpResponse<Person> resp = client.toBlocking()
                .exchange(HttpRequest.POST("/person", person), Person.class);

        assertEquals(201, resp.code());

        var body = resp.body();
        assertNotNull(body);
        assertTrue(body.getId() > 0);
        assertEquals(person.getFirstName(), body.getFirstName());
    }

    @Test
    public void testFindPerson() {
        var person = new Person("test", "test", "0000-0000");
        var resp = client.toBlocking()
                .exchange(HttpRequest.POST("/person", person), Person.class);

        var body = resp.body();
        assertNotNull(body);

        resp = client.toBlocking()
                .exchange(HttpRequest.GET("/person/" + body.getId()), Person.class);
        body = resp.body();

        assertNotNull(body);
        assertEquals(person.getFirstName(), body.getFirstName());
    }

    @Test
    public void testPersonNotFound() {

        assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking()
                    .retrieve(HttpRequest.GET("/person/100"));
        });
    }

    @Test
    public void testFindPhone() {
        var person = new Person("test", "test", "0000-0000");
        var resp = client.toBlocking()
                .exchange(HttpRequest.POST("/person", person), Person.class);

        var body = resp.body();
        assertNotNull(body);

        var phoneResp = client.toBlocking()
                .exchange(HttpRequest.GET("/person/" + body.getId() + "/number"), String.class);
        var phone = phoneResp.body();

        assertNotNull(body);
        assertEquals(person.getPhone(), phone);
    }

    @Test
    public void testDeletePerson() {
        var person = new Person("test", "test", "0000-0000");
        var resp = client.toBlocking()
                .exchange(HttpRequest.POST("/person", person), Person.class);

        var body = resp.body();
        assertNotNull(body);

        resp = client.toBlocking()
                .exchange(HttpRequest.DELETE("/person/" + body.getId()), Person.class);

        assertEquals(200, resp.code());
    }
}

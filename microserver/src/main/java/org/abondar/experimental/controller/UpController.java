package org.abondar.experimental.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.annotation.security.PermitAll;

@Controller("/up")
public class UpController {

    @Get(processes = MediaType.TEXT_PLAIN)
    @PermitAll
    public String upServer(){
        return "Server is up";
    }
}

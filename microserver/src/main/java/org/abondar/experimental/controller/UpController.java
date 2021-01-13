package org.abondar.experimental.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@Controller("/up")
public class UpController {

    @Get(processes = MediaType.TEXT_PLAIN)
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Check server status", description = "Server status")
    @ApiResponse(responseCode = "200")
    public String upServer(){
        return "Server is up";
    }
}

package org.abondar.experimental;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
        info = @Info(
                title = "microserver",
                version = "0.1",
                description="Demo Micronaut Server",
                license = @License(name = "MIT"),
                contact = @Contact(url = "https://github.com/abondar24",name="Alex Bondar",email ="abondar1992@gmail.com" )
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}

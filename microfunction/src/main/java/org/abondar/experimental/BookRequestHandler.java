package org.abondar.experimental;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;

import java.io.IOException;
import java.util.UUID;

@Introspected
public class BookRequestHandler extends MicronautRequestHandler<Book, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(Book input) {
        try{

          return   objectMapper.writeValueAsString(input);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        return "";
    }
}

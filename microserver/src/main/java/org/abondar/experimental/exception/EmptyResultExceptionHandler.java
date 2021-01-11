package org.abondar.experimental.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.data.exceptions.EmptyResultException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.HttpServerException;

import javax.inject.Singleton;


@Produces
@Singleton
@Requires(classes = {HttpServerException.class, ExceptionHandler.class})
public class EmptyResultExceptionHandler implements ExceptionHandler<HttpServerException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, HttpServerException exception) {
        return HttpResponse.serverError(exception.getMessage());
    }
}

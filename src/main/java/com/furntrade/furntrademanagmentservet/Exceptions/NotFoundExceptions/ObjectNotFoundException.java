package com.furntrade.furntrademanagmentservet.Exceptions.NotFoundExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Searched object could not be found")
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Long id) {
        super("Could not find object with " + id+ "id.");
    }
}

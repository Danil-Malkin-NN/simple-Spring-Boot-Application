package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST)
public class NoEntitiesException extends Exception {

    public NoEntitiesException(String message){
        super(message);
    }

}

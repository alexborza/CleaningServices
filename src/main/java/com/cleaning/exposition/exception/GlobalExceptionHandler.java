package com.cleaning.exposition.exception;

import com.cleaning.domain.users.*;
import com.cleaning.exposition.representation.response.shared.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class, UserNotFoundException.class, JobInformationNotFoundException.class})
    public final ResponseEntity<ExceptionRepresentation> handleAlreadyExistsException(
            UserAlreadyExistsException exception
    ) {

        return new ResponseEntity<>(
                new ExceptionRepresentation(exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}

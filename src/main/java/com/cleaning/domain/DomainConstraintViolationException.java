package com.cleaning.domain;

import javax.validation.*;

public class DomainConstraintViolationException extends RuntimeException {

    public DomainConstraintViolationException(String msg, ConstraintViolationException e) {
        super(msg, e);
    }
}

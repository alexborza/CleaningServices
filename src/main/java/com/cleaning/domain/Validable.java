package com.cleaning.domain;

import javax.validation.*;
import java.util.*;

public interface Validable {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    default <T> void validate(T o) {
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        this.handleViolations(violations);
    }

    default <T> void handleViolations(Set<ConstraintViolation<T>> violations) {
        if(!violations.isEmpty()) {
            String msg = String.format("{this: %s, violations: %s}", this, violations);
            throw new DomainConstraintViolationException(msg, new ConstraintViolationException(msg, violations));
        }
    }
}

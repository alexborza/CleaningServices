package com.cleaning.domain.appointment;

import javax.validation.*;
import java.lang.annotation.*;

public class TimeSlotValidator {

    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @javax.validation.Constraint(validatedBy = {TimeSlotRangeValidator.class})
    public @interface ValidConstraint {

        String message() default "Invalid Combination of starting and ending hours";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    public static class TimeSlotRangeValidator implements ConstraintValidator<ValidConstraint, TimeSlot> {

        @Override
        public boolean isValid(TimeSlot timeSlot, ConstraintValidatorContext constraintValidatorContext) {

            return timeSlot.getStartingHour() < timeSlot.getEndingHour();
        }
    }
}

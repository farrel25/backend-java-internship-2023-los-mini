package com.los.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFlowSequenceValidator.class)
public @interface UniqueFlowSequence {
    String message() default "Flow sequence must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

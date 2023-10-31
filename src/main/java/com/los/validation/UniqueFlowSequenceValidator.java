package com.los.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueFlowSequenceValidator implements ConstraintValidator<UniqueFlowSequence, Long> {
    public void initialize(UniqueFlowSequence constraintAnnotation) {
    }

    public boolean isValid(Long flowSequence, ConstraintValidatorContext context) {
        return isUnique(flowSequence);
    }

    private boolean isUnique(Long flowSequence) {
        return true; // Replace with your logic
    }
}

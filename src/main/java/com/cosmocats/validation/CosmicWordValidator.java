package com.cosmocats.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String lower = value.toLowerCase();
        return lower.contains("star") || lower.contains("galaxy") || lower.contains("comet");
    }
}




package com.cosmocats.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CosmicWordValidatorTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidCosmicWord() {
        TestDto testDto = new TestDto("This is a star");

        Set<ConstraintViolation<TestDto>> violations = validator.validate(testDto);
        assertTrue(violations.isEmpty(), "Should not have any violations for valid cosmic word.");
    }

    @Test
    public void testNullValue() {
        TestDto testDto = new TestDto("A new superstar");

        Set<ConstraintViolation<TestDto>> violations = validator.validate(testDto);
        assertTrue(violations.isEmpty(), "Should not have any violations for valid cosmic word.");
    }

    @Test
    public void testEmptyValue() {
        TestDto testDto = new TestDto("Anti-comet system");

        Set<ConstraintViolation<TestDto>> violations = validator.validate(testDto);
        assertTrue(violations.isEmpty(), "Should not have any violations for valid cosmic word.");
    }

    private static class TestDto {
        @CosmicWordCheck
        String name;

        public TestDto(String name) {
            this.name = name;
        }
    }
}


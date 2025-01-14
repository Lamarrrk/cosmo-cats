package com.cosmocats.exceptions;

import java.util.UUID;

public class ProductException extends RuntimeException {

    private static final String MESSAGE = "Product with id %s not found";

    public ProductException(UUID id) {
        super(String.format(MESSAGE, id));
    }
}


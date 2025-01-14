package com.cosmocats.exceptions;

public class CategoryException extends RuntimeException {
    public CategoryException(long id) {
        super(String.format("Category With ID - %d Not Found", id));
    }
}

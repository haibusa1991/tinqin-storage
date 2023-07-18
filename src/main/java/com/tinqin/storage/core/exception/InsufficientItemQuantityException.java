package com.tinqin.storage.core.exception;

public class InsufficientItemQuantityException extends RuntimeException {
    public InsufficientItemQuantityException(String referencedItemId) {
        super(String.format("Insufficient quantity of item with id '%s'", referencedItemId));
    }
}

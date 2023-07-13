package com.storage.exceptions;

public class InsuficientItemQuantityException extends Exception {
    public InsuficientItemQuantityException(String referencedItemId) {
        super(String.format("Insufficient quantity of item with id '%s'", referencedItemId));
    }
}

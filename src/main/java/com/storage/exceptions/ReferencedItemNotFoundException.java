package com.storage.exceptions;

public class ReferencedItemNotFoundException extends Exception {
    public ReferencedItemNotFoundException(String itemId) {
        super(String.format("Item with ID '%s' does not exist.", itemId));
    }
}

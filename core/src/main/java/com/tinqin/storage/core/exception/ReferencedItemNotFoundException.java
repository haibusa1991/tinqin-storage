package com.tinqin.storage.core.exception;

public class ReferencedItemNotFoundException extends RuntimeException {
    public ReferencedItemNotFoundException(String itemId) {
        super(String.format("Item with ID '%s' does not exist.", itemId));
    }
}

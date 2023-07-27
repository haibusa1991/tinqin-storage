package com.tinqin.storage.core.exception;

public class ReferencedItemNotFoundException extends RuntimeException {
    public ReferencedItemNotFoundException(String itemId) {
        super(String.format("Items with the following UUIDs do not exist: %s", itemId));
    }
}

package com.tinqin.storage.core.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String referencedItemId) {
        super(String.format("Item with id '%s' does not exist.", referencedItemId));
    }
}

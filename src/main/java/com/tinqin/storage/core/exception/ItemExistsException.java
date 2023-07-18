package com.tinqin.storage.core.exception;

public class ItemExistsException extends RuntimeException {
    public ItemExistsException(String referencedItemId) {
        super(String.format("Item with id '%s' already exists.", referencedItemId));
    }
}

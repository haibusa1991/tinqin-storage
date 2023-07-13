package com.storage.exceptions;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;

public class ItemExistsException extends Exception {
    public ItemExistsException(String referencedItemId) {
        super(String.format("Item with id '%s' already exists.", referencedItemId));
    }
}

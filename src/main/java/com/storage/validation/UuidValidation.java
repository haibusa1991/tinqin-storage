package com.storage.validation;

import com.storage.exceptions.NotUuidException;

import java.util.UUID;

public class UuidValidation {

    public static UUID verifyValidUuid(String referencedItemId) throws NotUuidException {
        try {
            return UUID.fromString(referencedItemId);
        } catch (IllegalArgumentException e) {
            throw new NotUuidException(e.getMessage());
        }
    }
}

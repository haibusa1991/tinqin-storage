package com.tinqin.storage.core.exception;

public class InvalidUuidException extends RuntimeException {
    public InvalidUuidException(String invalidUuid) {
        super(String.format("The following are not valid UUIDs: %s", invalidUuid));
    }
}

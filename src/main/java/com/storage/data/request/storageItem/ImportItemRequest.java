package com.storage.data.request.storageItem;


import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
@Getter
public class ImportItemRequest {

    @Positive
    private Integer quantity;
}
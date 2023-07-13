package com.storage.data.request.storageItem;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
@Getter
public class CreateStorageItemRequest {

    @NotBlank
    private String referencedItemId;

    @Positive
    private double price;
}
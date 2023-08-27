package com.tinqin.storage.api.operations.storageItem.createStorageItem;


import com.tinqin.storage.api.base.ProcessorInput;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class CreateStorageItemInput implements ProcessorInput {

    @UUID
    private String referencedItemId;

    @Positive
    private double price;
}
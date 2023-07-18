package com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice;


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
public class ChangeStorageItemPriceInput implements ProcessorInput {

    @UUID
    private String referencedItemId;

    @Positive
    private double price;
}
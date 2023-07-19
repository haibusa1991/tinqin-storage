package com.tinqin.storage.api.operations.storageItem.exportStorageItemQuantity;

import com.tinqin.storage.api.base.ProcessorInput;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
public class ExportStorageItemInput implements ProcessorInput {
    @UUID
    private String referencedItemId;

    @Positive
    private Integer quantity;
}
package com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class GetStorageItemByReferenceIdSingleItem implements ProcessorResult {
    private UUID id;
    private UUID referencedItemId;
    private double price;
    private int quantity;
}

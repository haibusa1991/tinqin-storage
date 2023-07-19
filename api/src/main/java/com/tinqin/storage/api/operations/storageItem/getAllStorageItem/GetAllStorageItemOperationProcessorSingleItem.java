package com.tinqin.storage.api.operations.storageItem.getAllStorageItem;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class GetAllStorageItemOperationProcessorSingleItem implements ProcessorResult {
    private UUID id;
    private UUID referencedItemId;
    private double price;
    private int quantity;
}

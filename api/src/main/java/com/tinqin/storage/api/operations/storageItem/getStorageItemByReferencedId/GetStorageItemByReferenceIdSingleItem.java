package com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.*;

import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
@AllArgsConstructor
public class GetStorageItemByReferenceIdSingleItem implements ProcessorResult {
    public GetStorageItemByReferenceIdSingleItem() {
    }

    private UUID id;
    private UUID referencedItemId;
    private double price;
    private int quantity;
}

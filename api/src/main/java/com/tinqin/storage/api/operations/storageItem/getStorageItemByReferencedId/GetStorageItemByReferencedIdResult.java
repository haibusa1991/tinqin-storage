package com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStorageItemByReferencedIdResult implements ProcessorResult {
    private UUID id;
    private UUID referencedItemId;
    private double price;
    private int quantity;
}

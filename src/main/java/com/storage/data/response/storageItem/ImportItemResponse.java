package com.storage.data.response.storageItem;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class ImportItemResponse {
    private UUID id;
    private UUID referencedItemId;
    private double price;
    private int quantity;
}

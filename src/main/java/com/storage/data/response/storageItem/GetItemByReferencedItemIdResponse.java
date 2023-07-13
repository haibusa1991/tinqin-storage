package com.storage.data.response.storageItem;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class GetItemByReferencedItemIdResponse {
    private String id;
    private String referencedItemId;
    private double price;
    private int quantity;
}

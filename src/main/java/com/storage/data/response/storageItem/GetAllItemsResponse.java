package com.storage.data.response.storageItem;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class GetAllItemsResponse {
    private List<GetItemByReferencedItemIdResponse> items;
}

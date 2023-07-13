package com.storage.services.storageItem.getItemService;

import com.storage.data.response.storageItem.GetItemByReferencedItemIdResponse;

public interface GetItemService {
    GetItemByReferencedItemIdResponse getItemByReferencedItemId(String referencedItemId);
}

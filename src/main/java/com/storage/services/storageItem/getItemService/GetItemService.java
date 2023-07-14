package com.storage.services.storageItem.getItemService;

import com.storage.data.model.StorageItem;
import com.storage.data.response.storageItem.GetAllItemsResponse;
import com.storage.data.response.storageItem.GetItemByReferencedItemIdResponse;
import com.storage.exceptions.NotUuidException;
import com.storage.exceptions.ReferencedItemNotFoundException;

import java.util.UUID;

public interface GetItemService {
    GetItemByReferencedItemIdResponse getItemByReferencedItemId(String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException;

    StorageItem getItemByUuid(UUID id) throws ReferencedItemNotFoundException;

    GetAllItemsResponse getAllItems();
}

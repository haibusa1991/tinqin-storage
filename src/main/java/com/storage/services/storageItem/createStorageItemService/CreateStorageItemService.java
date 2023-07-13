package com.storage.services.storageItem.createStorageItemService;

import com.storage.data.request.storageItem.CreateStorageItemRequest;
import com.storage.data.response.storageItem.CreateStorageItemResponse;
import com.storage.exceptions.ItemExistsException;

public interface CreateStorageItemService {
    CreateStorageItemResponse createNewStorageItem(CreateStorageItemRequest request) throws ItemExistsException;
}

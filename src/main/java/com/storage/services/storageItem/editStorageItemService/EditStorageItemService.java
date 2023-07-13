package com.storage.services.storageItem.editStorageItemService;

import com.storage.data.request.storageItem.EditItemPriceRequest;
import com.storage.data.request.storageItem.ExportItemRequest;
import com.storage.data.request.storageItem.ImportItemRequest;
import com.storage.data.response.storageItem.EditItemPriceResponse;
import com.storage.data.response.storageItem.ExportItemResponse;
import com.storage.data.response.storageItem.ImportItemResponse;
import com.storage.exceptions.InsuficientItemQuantityException;
import com.storage.exceptions.NotUuidException;
import com.storage.exceptions.ReferencedItemNotFoundException;

public interface EditStorageItemService {
    EditItemPriceResponse changeStorageItemPrice(EditItemPriceRequest request, String referencedItemId) throws ReferencedItemNotFoundException, NotUuidException;


    ImportItemResponse importItem(ImportItemRequest request, String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException;

    ExportItemResponse exportItem(ExportItemRequest request, String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException, InsuficientItemQuantityException;
}

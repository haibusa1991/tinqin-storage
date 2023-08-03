package com.tinqin.storage.restexport;

import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderInput;
import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderResult;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.GetAllStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Set;

@Headers({"Content-Type: application/json"})
public interface StorageItemRestExport {

    @RequestLine("POST /storage-items")
    CreateStorageItemResult createStorageItem(@Param CreateStorageItemInput input);

    @RequestLine("PUT /storage-items/{itemId}")
    EditStorageItemResult editStorageItem(@Param("itemId") String id, @Param EditStorageItemInput input);

    @RequestLine("GET /storage-items/{referencedItemId}")
    GetStorageItemByReferencedIdResult getItemByReferencedItemId(@Param("referencedItemId") Set<String> referencedItemId);

    @RequestLine("GET /storage-items")
    GetAllStorageItemResult getAllItems();

    @RequestLine("POST /order")
    PlaceOrderResult placeOrder(PlaceOrderInput input);

}

package com.tinqin.storage.restexport;

import java.util.Set;
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

@Headers({
    "Content-Type: application/json"
})
public interface StorageRestExport {

    @RequestLine("POST /")
    CreateStorageItemResult createStorageItem(@Param CreateStorageItemInput request);

    @RequestLine("GET /")
    GetStorageItemByReferencedIdResult getItemByReferencedItemId(@Param("referencedItemId") Set<String> referencedItemId);

    @RequestLine("PUT /")
    EditStorageItemResult editStorageItem(@Param("referencedItemId") String referencedItemId, @Param EditStorageItemInput input);

    @RequestLine("GET /")
    GetAllStorageItemResult getAllItems();

    @RequestLine("POST /order")
    PlaceOrderResult placeOrder(@Param PlaceOrderInput input);
}

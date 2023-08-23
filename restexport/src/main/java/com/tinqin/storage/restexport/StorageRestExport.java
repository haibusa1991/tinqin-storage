package com.tinqin.storage.restexport;

import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderInput;
import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({
    "Content-Type: application/json"
})
public interface StorageRestExport {

    @RequestLine("POST /order")
    PlaceOrderResult placeOrder(@Param PlaceOrderInput input);
}

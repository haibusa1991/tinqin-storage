package com.tinqin.storage.rest.controller;

import com.tinqin.restexport.annotation.RestExport;
import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderInput;
import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderOperation;
import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/order")
public class OrderController {
    private final PlaceOrderOperation placeOrder;

    @Operation(description = "Places order for all items in cart and returns invoice.", summary = "Orders all items in cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Item in cart with the specified id does not exist.", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Insufficient items in storage.", content = @Content()),
            @ApiResponse(responseCode = "200", description = "Order processed successfully.")})
    @RestExport
    @PostMapping
    ResponseEntity<PlaceOrderResult> placeOrder(@RequestBody PlaceOrderInput input) {
        return ResponseEntity.ok(this.placeOrder.process(input));
    }
}

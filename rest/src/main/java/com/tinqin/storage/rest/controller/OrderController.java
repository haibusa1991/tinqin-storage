package com.tinqin.storage.rest.controller;

import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderInput;
import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderOperation;
import com.tinqin.storage.api.operations.order.placeOrder.PlaceOrderResult;
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

    @PostMapping
    ResponseEntity<PlaceOrderResult> placeOrder(@RequestBody PlaceOrderInput input) {
        return ResponseEntity.ok(this.placeOrder.process(input));
    }
}

package com.tinqin.storage.api.operations.order.placeOrder;

import com.tinqin.storage.api.base.ProcessorInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class PlaceOrderInputCartItem implements ProcessorInput {

    private String referencedItemId;
    private Double price;
    private Integer quantity;
}
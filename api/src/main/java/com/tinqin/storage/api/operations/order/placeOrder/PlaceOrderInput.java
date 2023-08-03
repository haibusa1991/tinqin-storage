package com.tinqin.storage.api.operations.order.placeOrder;

import com.tinqin.storage.api.base.ProcessorInput;
import lombok.*;

import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderInput implements ProcessorInput {

    private List<PlaceOrderInputCartItem> cartItems;
}
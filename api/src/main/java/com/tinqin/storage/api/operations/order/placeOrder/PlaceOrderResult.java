package com.tinqin.storage.api.operations.order.placeOrder;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderResult implements ProcessorResult {
    private List<PlaceOrderResultSingleItem> order;
}

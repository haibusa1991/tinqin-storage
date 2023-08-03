package com.tinqin.storage.api.operations.order.placeOrder;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class PlaceOrderResultSingleItem implements ProcessorResult {
    private UUID id;
    private UUID referencedItemId;
    private Double price;
    private Integer quantity;
    private LocalDateTime timestamp;
    private UUID userId;
}

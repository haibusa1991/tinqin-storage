package com.tinqin.storage.api.operations.order.placeOrder;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderResultSingleItem implements ProcessorResult {
    private UUID id;
    private UUID referencedItemId;
    private Double price;
    private Integer quantity;
    private LocalDateTime timestamp;
    private UUID userId;
}

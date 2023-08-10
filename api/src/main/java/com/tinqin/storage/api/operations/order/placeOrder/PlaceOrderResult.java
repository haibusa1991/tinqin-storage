package com.tinqin.storage.api.operations.order.placeOrder;

import com.tinqin.storage.api.base.ProcessorResult;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceOrderResult implements ProcessorResult {
    private List<PlaceOrderResultSingleItem> items;
    private LocalDateTime timestamp;
    private UUID user;
    private Double orderPrice;
    private Double remainingUserCredit;
}

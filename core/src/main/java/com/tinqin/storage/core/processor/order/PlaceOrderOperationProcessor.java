package com.tinqin.storage.core.processor.order;

import com.tinqin.storage.api.operations.order.placeOrder.*;
import com.tinqin.storage.core.exception.InsufficientItemQuantityException;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import com.tinqin.storage.persistence.entity.Order;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.OrderRepository;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceOrderOperationProcessor implements PlaceOrderOperation {
    private final StorageItemRepository storageItemRepository;
    private final OrderRepository orderRepository;


    @Override
    public PlaceOrderResult process(PlaceOrderInput input) {

        return new PlaceOrderResult(input.getCartItems()
                .stream()
                .map(this::exportItem)
                .toList()
        );
    }

    private PlaceOrderResultSingleItem exportItem(PlaceOrderInputCartItem cartItem) {
        StorageItem item = this.storageItemRepository
                .findStorageItemByReferencedItemId(UUID.fromString(cartItem.getReferencedItemId()))
                .orElseThrow(() -> new ReferencedItemNotFoundException(cartItem.getReferencedItemId()));

        int quantityDifference = item.getQuantity() - cartItem.getQuantity();
        if (quantityDifference < 0) {
            throw new InsufficientItemQuantityException(cartItem.getReferencedItemId());
        }

        item.setQuantity(quantityDifference);

        StorageItem persistedStorageItem = this.storageItemRepository.save(item);

        Order order = Order.builder()
                .referencedItemId(persistedStorageItem.getReferencedItemId())
                .price(BigDecimal.valueOf(cartItem.getPrice()))
                .quantity(cartItem.getQuantity())
                .timestamp(LocalDateTime.now(Clock.systemUTC()))
                .user(UUID.fromString(cartItem.getUserId()))
                .build();

        Order persistedOrder = this.orderRepository.save(order);

        return PlaceOrderResultSingleItem.builder()
                .id(persistedOrder.getId())
                .referencedItemId(persistedOrder.getReferencedItemId())
                .price(persistedOrder.getPrice().doubleValue())
                .quantity(persistedOrder.getQuantity())
                .timestamp(persistedOrder.getTimestamp())
                .userId(persistedOrder.getUser())
                .build();
    }


}


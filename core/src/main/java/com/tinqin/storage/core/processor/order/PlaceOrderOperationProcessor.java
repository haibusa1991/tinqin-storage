package com.tinqin.storage.core.processor.order;

import com.tinqin.storage.api.operations.order.placeOrder.*;
import com.tinqin.storage.core.exception.InsufficientItemQuantityException;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import com.tinqin.storage.persistence.entity.Order;
import com.tinqin.storage.persistence.entity.OrderItem;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.OrderRepository;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceOrderOperationProcessor implements PlaceOrderOperation {
    private final StorageItemRepository storageItemRepository;
    private final OrderRepository orderRepository;

    @Override
    public PlaceOrderResult process(PlaceOrderInput input) {

        input.getCartItems()
                .stream()
                .map(this::mapPlaceOrderInputCartItemToOrderItem)
                .map(this::verifyItemExists)
                .forEach(this::exportCartItem);

        Set<OrderItem> orderItems = input.getCartItems()
                .stream()
                .map(this::mapPlaceOrderInputCartItemToOrderItem)
                .collect(Collectors.toSet());

        Order order = Order.builder()
                .timestamp(LocalDateTime.now(Clock.systemUTC()))
                .user(UUID.fromString(input.getUserId()))
                .items(orderItems)
                .orderPrice(orderItems.stream().map(this::getItemPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();

        Order persistedOrder = this.orderRepository.save(order);

        return PlaceOrderResult.builder()
                .items(persistedOrder.getItems().stream().map(this::mapOrderItemToPlaceOrderResultSingleItem).toList())
                .timestamp(persistedOrder.getTimestamp())
                .user(persistedOrder.getUser())
                .orderPrice(persistedOrder.getOrderPrice().doubleValue())
                .build();
    }

    private BigDecimal getItemPrice(OrderItem orderItem) {
        return orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
    }


    private void exportCartItem(OrderItem orderItem) {
        StorageItem storageItem = this.storageItemRepository
                .findStorageItemByReferencedItemId(orderItem.getReferencedItemId())
                .orElseThrow();
        Integer difference = storageItem.getQuantity() - orderItem.getQuantity();

        if (difference < 0) {
            throw new InsufficientItemQuantityException(storageItem.getReferencedItemId().toString());
        }

        storageItem.setQuantity(difference);
        this.storageItemRepository.save(storageItem);
    }

    private OrderItem verifyItemExists(OrderItem orderItem) {
        UUID itemId = orderItem.getReferencedItemId();

        if (!this.storageItemRepository.existsStorageItemByReferencedItemId(itemId)) {
            throw new ReferencedItemNotFoundException(itemId.toString());
        }

        return orderItem;
    }


    private OrderItem mapPlaceOrderInputCartItemToOrderItem(PlaceOrderInputCartItem item) {

        return OrderItem.builder()
                .referencedItemId(UUID.fromString(item.getReferencedItemId()))
                .price(BigDecimal.valueOf(item.getPrice()))
                .quantity(item.getQuantity())
                .build();
    }

    private PlaceOrderResultSingleItem mapOrderItemToPlaceOrderResultSingleItem(OrderItem orderItem) {
        return PlaceOrderResultSingleItem.builder()
                .referencedItemId(orderItem.getReferencedItemId())
                .price(orderItem.getPrice().doubleValue())
                .quantity(orderItem.getQuantity())
                .build();
    }

}


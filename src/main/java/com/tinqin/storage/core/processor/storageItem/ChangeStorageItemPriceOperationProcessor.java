package com.tinqin.storage.core.processor.storageItem;

import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceInput;
import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceOperation;
import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceResult;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeStorageItemPriceOperationProcessor implements ChangeStorageItemPriceOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public ChangeStorageItemPriceResult process(ChangeStorageItemPriceInput input) {

        StorageItem item = this.storageItemRepository
                .findStorageItemByReferencedItemId(UUID.fromString(input.getReferencedItemId()))
                .orElseThrow(() -> new ReferencedItemNotFoundException(input.getReferencedItemId()));

        item.setPrice(BigDecimal.valueOf(input.getPrice()));
        StorageItem persisted = this.storageItemRepository.save(item);

        return ChangeStorageItemPriceResult.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .quantity(persisted.getQuantity())
                .price(persisted.getPrice().doubleValue())
                .build();
    }
}

package com.tinqin.storage.core.processor.storageItem;

import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemResult;
import com.tinqin.storage.core.exception.ItemExistsException;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateStorageItemOperationProcessor implements CreateStorageItemOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public CreateStorageItemResult process(CreateStorageItemInput input) {
        Boolean isExistingItem = this.storageItemRepository.existsStorageItemByReferencedItemId(UUID.fromString(input.getReferencedItemId()));

        if (isExistingItem) {
            throw new ItemExistsException(input.getReferencedItemId());
        }

        StorageItem item = StorageItem.builder()
                .referencedItemId(UUID.fromString(input.getReferencedItemId()))
                .price(BigDecimal.valueOf(input.getPrice()))
                .build();

        StorageItem persisted = this.storageItemRepository.save(item);

        return CreateStorageItemResult.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .price(persisted.getPrice().doubleValue())
                .quantity(persisted.getQuantity())
                .build();
    }
}

package com.tinqin.storage.core.processor.storageItem;

import lombok.RequiredArgsConstructor;
import com.tinqin.storage.api.operations.storageItem.exportStorageItemQuantity.ExportStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.exportStorageItemQuantity.ExportStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.exportStorageItemQuantity.ExportStorageItemResult;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ExportStorageItemOperationProcessor implements ExportStorageItemOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public ExportStorageItemResult process(ExportStorageItemInput input) {
        StorageItem item = this.storageItemRepository
                .findStorageItemByReferencedItemId(UUID.fromString(input.getReferencedItemId()))
                .orElseThrow(() -> new ReferencedItemNotFoundException(input.getReferencedItemId()));


        item.decreaseQuantity(input.getQuantity());

        StorageItem persisted = this.storageItemRepository.save(item);

        return ExportStorageItemResult.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .quantity(persisted.getQuantity())
                .price(persisted.getPrice().doubleValue())
                .build();
    }
}

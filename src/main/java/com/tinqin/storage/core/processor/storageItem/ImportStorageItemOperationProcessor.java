package com.tinqin.storage.core.processor.storageItem;

import com.tinqin.storage.api.operations.storageItem.importStorageItem.ImportStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.importStorageItem.ImportStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.importStorageItem.ImportStorageItemResult;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImportStorageItemOperationProcessor implements ImportStorageItemOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public ImportStorageItemResult process(ImportStorageItemInput input) {
        StorageItem item = this.storageItemRepository
                .findStorageItemByReferencedItemId(UUID.fromString(input.getReferencedItemId()))
                .orElseThrow(() -> new ReferencedItemNotFoundException(input.getReferencedItemId()));


        item.increaseQuantity(input.getQuantity());

        StorageItem persisted = this.storageItemRepository.save(item);

        return ImportStorageItemResult.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .quantity(persisted.getQuantity())
                .price(persisted.getPrice().doubleValue())
                .build();
    }
}

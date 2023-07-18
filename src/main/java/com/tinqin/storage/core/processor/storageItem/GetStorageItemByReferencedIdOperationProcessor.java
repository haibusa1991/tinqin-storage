package com.tinqin.storage.core.processor.storageItem;

import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdInput;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdOperation;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdResult;
import com.tinqin.storage.core.exception.ItemNotFoundException;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetStorageItemByReferencedIdOperationProcessor implements GetStorageItemByReferencedIdOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public GetStorageItemByReferencedIdResult process(GetStorageItemByReferencedIdInput input) {
        Optional<StorageItem> storageItemOptional = this.storageItemRepository.findStorageItemByReferencedItemId(UUID.fromString(input.getId()));

        if (storageItemOptional.isEmpty()) {
            throw new ItemNotFoundException(input.getId());
        }

        StorageItem item = storageItemOptional.get();
        return GetStorageItemByReferencedIdResult.builder()
                .id(item.getId())
                .referencedItemId(item.getReferencedItemId())
                .quantity(item.getQuantity())
                .price(item.getPrice().doubleValue())
                .build();
    }
}

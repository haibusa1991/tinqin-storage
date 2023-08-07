package com.tinqin.storage.core.processor.storageItem;

import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferenceIdSingleItem;
import com.tinqin.storage.core.exception.InvalidUuidException;
import com.tinqin.storage.core.exception.ItemNotFoundException;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import lombok.RequiredArgsConstructor;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdInput;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdOperation;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdResult;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetStorageItemByReferencedIdOperationProcessor implements GetStorageItemByReferencedIdOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public GetStorageItemByReferencedIdResult process(GetStorageItemByReferencedIdInput input) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> invalidUuids = input.getId()
                .stream()
                .map(this::validateUuid)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (!invalidUuids.isEmpty()) {
            throw new InvalidUuidException(String.join("; ", invalidUuids));
        }

        Set<StorageItem> storageItems = this.storageItemRepository.findByReferencedItemIdIn(input.getId().stream().map(UUID::fromString).toList());

        if (storageItems.size() != input.getId().size()) {
            List<String> foundItems = storageItems.stream()
                    .map(StorageItem::getReferencedItemId)
                    .map(UUID::toString)
                    .toList();

            String missingIds = input.getId()
                    .stream()
                    .filter(e -> !foundItems.contains(e))
                    .collect(Collectors.joining("; "));

            throw new ReferencedItemNotFoundException(missingIds);
        }

        return GetStorageItemByReferencedIdResult.builder().items(
                        storageItems.stream()
                                .map(this::mapStorageItemToGetStorageItemByReferenceIdSingleItem)
                                .toList())
                .build();

    }

    private Optional<String> validateUuid(String id) {
        Optional<String> result = Optional.empty();
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            result = Optional.of(id);
        }
        return result;
    }

    private GetStorageItemByReferenceIdSingleItem mapStorageItemToGetStorageItemByReferenceIdSingleItem(StorageItem item) {
        return GetStorageItemByReferenceIdSingleItem.builder()
                .id(item.getId())
                .referencedItemId(item.getReferencedItemId())
                .quantity(item.getQuantity())
                .price(item.getPrice().doubleValue())
                .build();
    }
}


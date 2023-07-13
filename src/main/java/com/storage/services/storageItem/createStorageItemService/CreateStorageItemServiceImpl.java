package com.storage.services.storageItem.createStorageItemService;

import com.storage.data.model.StorageItem;
import com.storage.data.request.storageItem.CreateStorageItemRequest;
import com.storage.data.response.storageItem.CreateStorageItemResponse;
import com.storage.exceptions.ItemExistsException;
import com.storage.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateStorageItemServiceImpl implements CreateStorageItemService {

    private final StorageItemRepository storageItemRepository;

    @Override
    public CreateStorageItemResponse createNewStorageItem(CreateStorageItemRequest request) throws ItemExistsException {
        Boolean isExistingItem = this.storageItemRepository.existsStorageItemByReferencedItemId(UUID.fromString(request.getReferencedItemId()));

        if (isExistingItem) {
            throw new ItemExistsException(request.getReferencedItemId());
        }

        StorageItem item = StorageItem.builder()
                .referencedItemId(UUID.fromString(request.getReferencedItemId()))
                .price(BigDecimal.valueOf(request.getPrice()))
                .build();

        StorageItem persisted = this.storageItemRepository.save(item);

        return CreateStorageItemResponse.builder()
                .id(persisted.getId().toString())
                .referencedItemId(request.getReferencedItemId())
                .price(persisted.getPrice().doubleValue())
                .quantity(persisted.getQuantity())
                .build();
    }
}

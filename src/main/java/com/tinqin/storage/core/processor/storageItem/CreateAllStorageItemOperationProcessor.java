package com.tinqin.storage.core.processor.storageItem;

import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.CreateAllStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.GetAllStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.GetAllStorageItemResult;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateAllStorageItemOperationProcessor implements CreateAllStorageItemOperation {
    private final StorageItemRepository storageItemRepository;

    @Override
    public GetAllStorageItemResult process(GetAllStorageItemInput input) {

        return GetAllStorageItemResult.builder().items(
                this.storageItemRepository.findAll()
                        .stream()
                        .map(this::mapStorageItemToGetAllStorageItemOperationProcessorSingleItem)
                        .collect(Collectors.toSet())
        ).build();

    }

    private GetAllStorageItemOperationProcessorSingleItem mapStorageItemToGetAllStorageItemOperationProcessorSingleItem(StorageItem item) {
        return GetAllStorageItemOperationProcessorSingleItem.builder()
                .id(item.getId())
                .referencedItemId(item.getReferencedItemId())
                .quantity(item.getQuantity())
                .price(item.getPrice().doubleValue())
                .build();
    }
}

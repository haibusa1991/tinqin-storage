package com.storage.services.storageItem.getItemService;

import com.storage.data.model.StorageItem;
import com.storage.data.response.storageItem.ExportItemResponse;
import com.storage.data.response.storageItem.GetAllItemsResponse;
import com.storage.data.response.storageItem.GetItemByReferencedItemIdResponse;
import com.storage.exceptions.NotUuidException;
import com.storage.exceptions.ReferencedItemNotFoundException;
import com.storage.repository.StorageItemRepository;
import com.storage.validation.UuidValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetItemServiceImpl implements GetItemService {
    private final StorageItemRepository storageItemRepository;


    @Override
    public GetItemByReferencedItemIdResponse getItemByReferencedItemId(String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException {
        UUID referencedItemIdUuid = UuidValidation.verifyValidUuid(referencedItemId);
        StorageItem item = this.getItemByUuid(referencedItemIdUuid);

        return this.mapStorageItemToGetItemByReferencedItemIdResponse(item);
    }

    @Override
    public StorageItem getItemByUuid(UUID id) throws ReferencedItemNotFoundException {
        Optional<StorageItem> itemOptional = this.storageItemRepository.findStorageItemByReferencedItemId(id);

        if (itemOptional.isEmpty()) {
            throw new ReferencedItemNotFoundException(id.toString());
        }

        return itemOptional.get();
    }

    @Override
    public GetAllItemsResponse getAllItems() {
        List<GetItemByReferencedItemIdResponse> allItems = this.storageItemRepository.findAll()
                .stream()
                .map(this::mapStorageItemToGetItemByReferencedItemIdResponse)
                .toList();

        return GetAllItemsResponse.builder().items(allItems).build();
    }

    private GetItemByReferencedItemIdResponse mapStorageItemToGetItemByReferencedItemIdResponse(StorageItem item) {
        return GetItemByReferencedItemIdResponse.builder()
                .id(item.getId())
                .referencedItemId(item.getReferencedItemId())
                .quantity(item.getQuantity())
                .price(item.getPrice().doubleValue())
                .build();
    }


}

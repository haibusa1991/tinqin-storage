package com.storage.services.storageItem.editStorageItemService;

import com.storage.data.model.StorageItem;
import com.storage.data.request.storageItem.EditItemPriceRequest;
import com.storage.data.request.storageItem.ExportItemRequest;
import com.storage.data.request.storageItem.ImportItemRequest;
import com.storage.data.response.storageItem.EditItemPriceResponse;
import com.storage.data.response.storageItem.ExportItemResponse;
import com.storage.data.response.storageItem.ImportItemResponse;
import com.storage.exceptions.InsuficientItemQuantityException;
import com.storage.exceptions.NotUuidException;
import com.storage.exceptions.ReferencedItemNotFoundException;
import com.storage.repository.StorageItemRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditStorageItemServiceImpl implements EditStorageItemService {
    private final StorageItemRepository storageItemRepository;

    @Override
    public EditItemPriceResponse changeStorageItemPrice(EditItemPriceRequest request, String referencedItemId) throws ReferencedItemNotFoundException, NotUuidException {

        UUID referencedItemIdUuid = this.verifyValidUuid(referencedItemId);
        StorageItem item = this.getItemByUuid(referencedItemIdUuid);

        item.setPrice(BigDecimal.valueOf(request.getPrice()));
        StorageItem persisted = this.storageItemRepository.save(item);

        return EditItemPriceResponse.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .quantity(persisted.getQuantity())
                .price(persisted.getPrice().doubleValue())
                .build();
    }

    @Override
    public ImportItemResponse importItem(ImportItemRequest request, String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException {

        UUID referencedItemIdUuid = this.verifyValidUuid(referencedItemId);
        StorageItem item = this.getItemByUuid(referencedItemIdUuid);

        item.increaseQuantity(request.getQuantity());

        StorageItem persisted = this.storageItemRepository.save(item);

        return ImportItemResponse.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .quantity(persisted.getQuantity())
                .price(persisted.getPrice().doubleValue())
                .build();
    }

    @Override
    public ExportItemResponse exportItem(ExportItemRequest request, String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException, InsuficientItemQuantityException {

        UUID referencedItemIdUuid = this.verifyValidUuid(referencedItemId);
        StorageItem item = this.getItemByUuid(referencedItemIdUuid);

        if (request.getQuantity() > item.getQuantity()) {
            throw new InsuficientItemQuantityException(referencedItemId);
        }

        item.decreaseQuantity(request.getQuantity());

        StorageItem persisted = this.storageItemRepository.save(item);

        return ExportItemResponse.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .quantity(persisted.getQuantity())
                .price(persisted.getPrice().doubleValue())
                .build();
    }


    private UUID verifyValidUuid(String referencedItemId) throws NotUuidException {
        try {
            return UUID.fromString(referencedItemId);
        } catch (IllegalArgumentException e) {
            throw new NotUuidException(e.getMessage());
        }
    }

    private StorageItem getItemByUuid(UUID id) throws ReferencedItemNotFoundException {
        Optional<StorageItem> itemOptional = this.storageItemRepository.findStorageItemByReferencedItemId(id);

        if (itemOptional.isEmpty()) {
            throw new ReferencedItemNotFoundException(id.toString());
        }

        return itemOptional.get();
    }
}

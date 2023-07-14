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
import com.storage.services.storageItem.getItemService.GetItemService;
import com.storage.validation.UuidValidation;
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
    private final GetItemService getItemService;

    @Override
    public EditItemPriceResponse changeStorageItemPrice(EditItemPriceRequest request, String referencedItemId) throws ReferencedItemNotFoundException, NotUuidException {

        UUID referencedItemIdUuid = UuidValidation.verifyValidUuid(referencedItemId);
        StorageItem item = getItemService.getItemByUuid(referencedItemIdUuid);

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

        UUID referencedItemIdUuid = UuidValidation.verifyValidUuid(referencedItemId);
        StorageItem item = getItemService.getItemByUuid(referencedItemIdUuid);

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

        UUID referencedItemIdUuid = UuidValidation.verifyValidUuid(referencedItemId);
        StorageItem item = getItemService.getItemByUuid(referencedItemIdUuid);

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
}

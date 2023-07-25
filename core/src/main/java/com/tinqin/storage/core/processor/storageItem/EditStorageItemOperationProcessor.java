package com.tinqin.storage.core.processor.storageItem;

import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemResult;
import com.tinqin.storage.core.exception.InsufficientItemQuantityException;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import com.tinqin.storage.persistence.entity.StorageItem;
import com.tinqin.storage.persistence.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EditStorageItemOperationProcessor implements EditStorageItemOperation {
    private final StorageItemRepository storageItemRepository;


    @Override
    public EditStorageItemResult process(EditStorageItemInput input) {
        StorageItem item = this.storageItemRepository.findStorageItemByReferencedItemId(input.getReferencedItemId())
                .orElseThrow(() -> new ReferencedItemNotFoundException(input.getReferencedItemId().toString()));

        if (input.getPrice() != null) {
            item.setPrice(BigDecimal.valueOf(input.getPrice()));
        }

        if (input.getQuantityDifference() != null) {
            Integer quantity = item.getQuantity() + input.getQuantityDifference();

            if (quantity < 0) {
                throw new InsufficientItemQuantityException(input.getReferencedItemId().toString());
            }

            item.setQuantity(quantity);
        }

        StorageItem persisted = this.storageItemRepository.save(item);

        return EditStorageItemResult.builder()
                .id(persisted.getId())
                .referencedItemId(persisted.getReferencedItemId())
                .price(persisted.getPrice().doubleValue())
                .quantity(persisted.getQuantity())
                .build();
    }


}

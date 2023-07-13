package com.storage.services.storageItem.getItemService;

import com.storage.data.response.storageItem.GetItemByReferencedItemIdResponse;
import com.storage.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemServiceImpl implements GetItemService {
    private final StorageItemRepository storageItemRepository;


    @Override
    public GetItemByReferencedItemIdResponse getItemByReferencedItemId(String referencedItemId) {
//TODO
        return null;
    }
}

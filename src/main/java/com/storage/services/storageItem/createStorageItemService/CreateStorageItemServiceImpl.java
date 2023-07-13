package com.storage.services.storageItem.createStorageItemService;

import com.storage.data.request.storageItem.CreateStorageItemRequest;
import com.storage.data.response.storageItem.CreateStorageItemResponse;
import com.storage.repository.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateStorageItemServiceImpl implements CreateStorageItemService {

    private final StorageItemRepository storageItemRepository;

    @Override
    public CreateStorageItemResponse createNewStorageItem(CreateStorageItemRequest request) {
        return null;
    }
}

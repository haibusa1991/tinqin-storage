package com.storage.controller;

import com.storage.data.request.storageItem.CreateStorageItemRequest;
import com.storage.data.response.storageItem.CreateStorageItemResponse;
import com.storage.services.storageItem.createStorageItemService.CreateStorageItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage-items")
public class StorageItemController {

    private final CreateStorageItemService createStorageItemService;

    @PostMapping
    public ResponseEntity<CreateStorageItemResponse> createStorageItem(@RequestBody @Valid CreateStorageItemRequest request) {
        return ResponseEntity.ok(this.createStorageItemService.createNewStorageItem(request));
    }

}

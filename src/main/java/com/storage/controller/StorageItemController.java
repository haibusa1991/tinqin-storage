package com.storage.controller;

import com.storage.data.request.storageItem.CreateStorageItemRequest;
import com.storage.data.request.storageItem.EditItemPriceRequest;
import com.storage.data.request.storageItem.ExportItemRequest;
import com.storage.data.request.storageItem.ImportItemRequest;
import com.storage.data.response.storageItem.*;
import com.storage.exceptions.InsuficientItemQuantityException;
import com.storage.exceptions.ItemExistsException;
import com.storage.exceptions.NotUuidException;
import com.storage.exceptions.ReferencedItemNotFoundException;
import com.storage.services.storageItem.createStorageItemService.CreateStorageItemService;
import com.storage.services.storageItem.editStorageItemService.EditStorageItemService;
import com.storage.services.storageItem.getItemService.GetItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage-items")
public class StorageItemController {

    private final CreateStorageItemService createStorageItemService;
    private final EditStorageItemService editStorageItemService;
    private final GetItemService getItemService;

    @PostMapping
    public ResponseEntity<CreateStorageItemResponse> createStorageItem(@RequestBody @Valid CreateStorageItemRequest request) throws ItemExistsException {
        return new ResponseEntity<>(this.createStorageItemService.createNewStorageItem(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{referencedItemId}/edit-price")
    public ResponseEntity<EditItemPriceResponse> editItemPrice(@RequestBody @Valid EditItemPriceRequest request, @PathVariable String referencedItemId) throws ReferencedItemNotFoundException, NotUuidException {
        return ResponseEntity.ok(this.editStorageItemService.changeStorageItemPrice(request, referencedItemId));
    }

    @PatchMapping("/{referencedItemId}/import")
    public ResponseEntity<ImportItemResponse> importItem(@RequestBody @Valid ImportItemRequest request, @PathVariable String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException {
        return ResponseEntity.ok(this.editStorageItemService.importItem(request, referencedItemId));
    }

    @PatchMapping("/{referencedItemId}/export")
    public ResponseEntity<ExportItemResponse> exportItem(@RequestBody @Valid ExportItemRequest request, @PathVariable String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException, InsuficientItemQuantityException {
        return ResponseEntity.ok(this.editStorageItemService.exportItem(request, referencedItemId));
    }

    @GetMapping("/{referencedItemId}")
    public ResponseEntity<GetItemByReferencedItemIdResponse> GetItemByReferencedItemId(@PathVariable String referencedItemId) throws NotUuidException, ReferencedItemNotFoundException {
        return ResponseEntity.ok(this.getItemService.getItemByReferencedItemId(referencedItemId));
    }

    @GetMapping
    public ResponseEntity<GetAllItemsResponse> getAllItems(){
        return ResponseEntity.ok(this.getItemService.getAllItems());
    }

}

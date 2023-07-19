package com.tinqin.storage.rest.controller;


import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceInput;
import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceOperation;
import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceResult;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.exportStorageItemQuantity.ExportStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.exportStorageItemQuantity.ExportStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.exportStorageItemQuantity.ExportStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.CreateAllStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.GetAllStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.GetAllStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdInput;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdOperation;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdResult;
import com.tinqin.storage.api.operations.storageItem.importStorageItem.ImportStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.importStorageItem.ImportStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.importStorageItem.ImportStorageItemResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage-items")
public class StorageItemController {
    private final CreateStorageItemOperation createNewStorageItem;
    private final GetStorageItemByReferencedIdOperation getStorageItemByReferencedId;
    private final CreateAllStorageItemOperation getAllItems;
    private final ChangeStorageItemPriceOperation changeStorageItemPrice;
    private final ImportStorageItemOperation importStorageItem;
    private final ExportStorageItemOperation exportStorageItem;

    @PostMapping
    public ResponseEntity<CreateStorageItemResult> createStorageItem(@RequestBody @Valid CreateStorageItemInput request) {
        return new ResponseEntity<>(this.createNewStorageItem.process(request), HttpStatus.CREATED);
    }

    @PatchMapping("/edit-price")
    public ResponseEntity<ChangeStorageItemPriceResult> changeStorageItemPrice(@RequestBody @Valid ChangeStorageItemPriceInput input) {
        return ResponseEntity.ok(this.changeStorageItemPrice.process(input));
    }

    @PatchMapping("/import")
    public ResponseEntity<ImportStorageItemResult> importStorageItem(@RequestBody @Valid ImportStorageItemInput input) {
        return ResponseEntity.ok(this.importStorageItem.process(input));
    }

    @PatchMapping("/export")
    public ResponseEntity<ExportStorageItemResult> exportItem(@RequestBody @Valid ExportStorageItemInput input) {
        return ResponseEntity.ok(this.exportStorageItem.process(input));
    }

    @GetMapping("/{referencedItemId}")
    public ResponseEntity<GetStorageItemByReferencedIdResult> GetItemByReferencedItemId(@PathVariable String referencedItemId) {
        return ResponseEntity.ok(this.getStorageItemByReferencedId.process(GetStorageItemByReferencedIdInput.builder().id(referencedItemId).build()));
    }

    @GetMapping
    public ResponseEntity<GetAllStorageItemResult> getAllItems() {
        return ResponseEntity.ok(this.getAllItems.process(new GetAllStorageItemInput()));
    }


}

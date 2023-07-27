package com.tinqin.storage.rest.controller;


import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceInput;
import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceOperation;
import com.tinqin.storage.api.operations.storageItem.changeStorageItemPrice.ChangeStorageItemPriceResult;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemResult;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/storage-items")
public class StorageItemController {
    private final CreateStorageItemOperation createNewStorageItem;
    private final GetStorageItemByReferencedIdOperation getStorageItemByReferencedId;
    private final CreateAllStorageItemOperation getAllItems;
    private final EditStorageItemOperation editStorageItem;
//    private final ChangeStorageItemPriceOperation changeStorageItemPrice;
//    private final ImportStorageItemOperation importStorageItem;
//    private final ExportStorageItemOperation exportStorageItem;


    //    @PatchMapping("/edit-price")
//    public ResponseEntity<ChangeStorageItemPriceResult> changeStorageItemPrice(@RequestBody @Valid ChangeStorageItemPriceInput input) {
//        return ResponseEntity.ok(this.changeStorageItemPrice.process(input));
//    }
//
//    @PatchMapping("/import")
//    public ResponseEntity<ImportStorageItemResult> importStorageItem(@RequestBody @Valid ImportStorageItemInput input) {
//        return ResponseEntity.ok(this.importStorageItem.process(input));
//    }
//
//    @PatchMapping("/export")
//    public ResponseEntity<ExportStorageItemResult> exportStorageItem(@RequestBody @Valid ExportStorageItemInput input) {
//        return ResponseEntity.ok(this.exportStorageItem.process(input));
//    }
    @PostMapping
    public ResponseEntity<CreateStorageItemResult> createStorageItem(@RequestBody @Valid CreateStorageItemInput request) {
        return new ResponseEntity<>(this.createNewStorageItem.process(request), HttpStatus.CREATED);
    }

    @GetMapping("/{referencedItemId}")
    public ResponseEntity<GetStorageItemByReferencedIdResult> getItemByReferencedItemId(@PathVariable Set<String> referencedItemId) {
        return ResponseEntity.ok(this.getStorageItemByReferencedId.process(GetStorageItemByReferencedIdInput.builder().id(referencedItemId).build()));
    }

    @PutMapping("/{referencedItemId}")
    public ResponseEntity<EditStorageItemResult> editStorageItem(@PathVariable @org.hibernate.validator.constraints.UUID String referencedItemId, @RequestBody @Valid EditStorageItemInput input) {
        input.setReferencedItemId(UUID.fromString(referencedItemId));
        return ResponseEntity.ok(this.editStorageItem.process(input));
    }

    @GetMapping
    public ResponseEntity<GetAllStorageItemResult> getAllItems() {
        return ResponseEntity.ok(this.getAllItems.process(new GetAllStorageItemInput()));
    }


}

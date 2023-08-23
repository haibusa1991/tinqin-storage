package com.tinqin.storage.rest.controller;


import com.tinqin.restexport.annotation.RestExport;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.createStorageItem.CreateStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.editStorageItem.EditStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.CreateAllStorageItemOperation;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.GetAllStorageItemInput;
import com.tinqin.storage.api.operations.storageItem.getAllStorageItem.GetAllStorageItemResult;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdInput;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdOperation;
import com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId.GetStorageItemByReferencedIdResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Operation(description = "Creates and returns storage item referencing the store item with 0 quantity.", summary = "Creates new item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Referenced item not found.", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Specified id of the referenced item is not valid UUID.", content = @Content()),
            @ApiResponse(responseCode = "200", description = "Item created successfully.")})
    @RestExport
    @PostMapping
    public ResponseEntity<CreateStorageItemResult> createStorageItem(@RequestBody @Valid CreateStorageItemInput request) {
        return new ResponseEntity<>(this.createNewStorageItem.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "Returns storage for referenced item id.", summary = "Gets storage for specified item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Referenced item not found.", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Specified id of the referenced item is not valid UUID.", content = @Content()),
            @ApiResponse(responseCode = "200", description = "Returns storage.")})
    @RestExport
    @GetMapping("/{referencedItemId}")
    public ResponseEntity<GetStorageItemByReferencedIdResult> getItemByReferencedItemId(@PathVariable Set<String> referencedItemId) {
        return ResponseEntity.ok(this.getStorageItemByReferencedId.process(GetStorageItemByReferencedIdInput.builder().id(referencedItemId).build()));
    }

    @Operation(description = "Edits and returns storage for referenced item id. Both quantity and price are optional.", summary = "Gets storage for specified item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Referenced item not found.", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Specified id of the referenced item is not valid UUID.", content = @Content()),
            @ApiResponse(responseCode = "200", description = "Returns storage.")})
    @RestExport
    @PutMapping("/{referencedItemId}")
    public ResponseEntity<EditStorageItemResult> editStorageItem(@PathVariable @org.hibernate.validator.constraints.UUID String referencedItemId, @RequestBody @Valid EditStorageItemInput input) {
        input.setReferencedItemId(UUID.fromString(referencedItemId));
        return ResponseEntity.ok(this.editStorageItem.process(input));
    }

    @Operation(description = "Returns all storage items.", summary = "Gets all items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns storage. Returns empty array if no items exist.")})
    @RestExport
    @GetMapping
    public ResponseEntity<GetAllStorageItemResult> getAllItems() {
        return ResponseEntity.ok(this.getAllItems.process(new GetAllStorageItemInput()));
    }


}

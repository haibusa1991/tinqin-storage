package com.tinqin.storage.api.operations.storageItem.editStorageItem;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqin.storage.api.base.ProcessorInput;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Builder
@Getter
public class EditStorageItemInput implements ProcessorInput {

    @JsonIgnore
    private UUID referencedItemId;

    @Positive
    @Nullable
    private Double price;

    @Nullable
    private Integer quantityDifference;
}
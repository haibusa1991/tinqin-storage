package com.tinqin.storage.api.operations.storageItem.getAllStorageItem;

import com.tinqin.storage.api.base.ProcessorResult;
import com.tinqin.storage.core.processor.storageItem.GetAllStorageItemOperationProcessorSingleItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class GetAllStorageItemResult implements ProcessorResult {
    private Set<GetAllStorageItemOperationProcessorSingleItem> items;
}

package com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId;

import com.tinqin.storage.api.base.ProcessorInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@Builder
public class GetStorageItemByReferencedIdInput implements ProcessorInput {

    @UUID
    private String id;
}
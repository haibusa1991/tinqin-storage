package com.tinqin.storage.api.operations.storageItem.getStorageItemByReferencedId;

import com.tinqin.storage.api.base.ProcessorInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
public class GetStorageItemByReferencedIdInput implements ProcessorInput {

    private Set<String> id;
}
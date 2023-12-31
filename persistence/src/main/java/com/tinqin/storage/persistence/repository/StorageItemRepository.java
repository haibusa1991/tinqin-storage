package com.tinqin.storage.persistence.repository;

import com.tinqin.storage.persistence.entity.StorageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface StorageItemRepository extends JpaRepository<StorageItem, UUID> {

    Optional<StorageItem> findStorageItemByReferencedItemId(UUID id);

    Set<StorageItem> findByReferencedItemIdIn(List<UUID> uuids);

    Boolean existsStorageItemByReferencedItemId(UUID id);

}

package com.storage.repository;

import com.storage.data.model.StorageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageItemRepository extends JpaRepository<StorageItem, UUID> {
}

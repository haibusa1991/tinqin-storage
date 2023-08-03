package com.tinqin.storage.persistence.repository;

import com.tinqin.storage.persistence.entity.Order;
import com.tinqin.storage.persistence.entity.StorageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}

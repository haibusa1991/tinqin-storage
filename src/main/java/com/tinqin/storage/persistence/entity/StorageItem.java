package com.tinqin.storage.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "storage_items")
public class StorageItem {

    @Builder
    public StorageItem(UUID referencedItemId, BigDecimal price) {
        this.referencedItemId = referencedItemId;
        this.price = price;
        this.quantity = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private UUID referencedItemId;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer quantity;

    public void increaseQuantity(Integer amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(Integer amount) {
        this.quantity -= amount;
    }

}

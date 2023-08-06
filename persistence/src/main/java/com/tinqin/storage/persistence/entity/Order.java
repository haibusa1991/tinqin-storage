package com.tinqin.storage.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Builder
    public Order(Set<OrderItem> items, LocalDateTime timestamp, UUID user, BigDecimal orderPrice) {
        this.items = items;
        this.timestamp = timestamp;
        this.user = user;
        this.orderPrice = orderPrice;
    }

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private Set<OrderItem> items;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private UUID user;

    @Column
    private BigDecimal orderPrice;
}

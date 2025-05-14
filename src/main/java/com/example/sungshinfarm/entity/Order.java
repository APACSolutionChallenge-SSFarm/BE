package com.example.sungshinfarm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    @Builder.Default
    private Status orderStatus = Status.PENDING;

    @Column(name = "ordered_at")
    @Builder.Default
    private LocalDateTime orderedAt = LocalDateTime.now();

    public enum Status {
        PENDING, PAID, SHIPPED, DELIVERED, CANCELLED
    }
}

package com.example.sungshinfarm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "farm_products")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(length = 100, nullable = false)
    private String name;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "quality_grade", nullable = false)
    private QualityGrade qualityGrade;

    @Column(name = "recommended_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal recommendedPrice;

    @Column(nullable = false)
    private int stock;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum QualityGrade {
        HIGH, MEDIUM, LOW, UGLY
    }
}


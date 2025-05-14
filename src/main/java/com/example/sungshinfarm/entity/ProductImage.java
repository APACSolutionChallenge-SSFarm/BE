package com.example.sungshinfarm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_images")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", length = 255, nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "detected_quality", nullable = false)
    private FarmProduct.QualityGrade detectedQuality;

    private Float confidence;

    @Column(name = "uploaded_at")
    @Builder.Default
    private LocalDateTime uploadedAt = LocalDateTime.now();
}


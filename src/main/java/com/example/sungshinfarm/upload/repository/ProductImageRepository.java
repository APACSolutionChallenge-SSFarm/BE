package com.example.sungshinfarm.upload.repository;

import com.example.sungshinfarm.upload.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
package com.example.sungshinfarm.repository;

import com.example.sungshinfarm.entity.FarmProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmProductRepository extends JpaRepository<FarmProduct, Long> {
}

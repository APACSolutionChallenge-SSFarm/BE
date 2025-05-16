package com.example.sungshinfarm.repository;

import com.example.sungshinfarm.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // userId로 Store 조회
    //Optional<Store> findByUserId(Long userId);
    Optional<Store> findByFarmer_Id(Long farmerId);
}


package com.example.sungshinfarm.controller;

import com.example.sungshinfarm.dto.AiResultDto;
import com.example.sungshinfarm.dto.FarmProductRequestDto;
import com.example.sungshinfarm.entity.FarmProduct;
import com.example.sungshinfarm.entity.Store;
import com.example.sungshinfarm.repository.FarmProductRepository;
import com.example.sungshinfarm.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {
    private final FarmProductRepository farmProductRepository;
    private final StoreRepository storeRepository;

    // 등록폼 진입 (품목명, 품질등급을 받아서 등록폼 초기 데이터 제공)
    @GetMapping("/register-form")
    public ResponseEntity<AiResultDto> showRegisterForm(@RequestParam String name, @RequestParam String qualityGrade, @RequestParam BigDecimal recommendedPrice) {
        AiResultDto dto = new AiResultDto(name, qualityGrade, recommendedPrice);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/save")
    public ResponseEntity<String> registerProduct(
            @RequestBody FarmProductRequestDto requestDto,
            @RequestParam Long userId  // 프론트에서 사용자 ID 보내는 방식
    ) {
        // 1. 사용자 ID로 Store 찾기
        Store store = storeRepository.findByFarmer_Id(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 상점이 존재하지 않습니다."));

        // 2. 품질등급 문자열을 enum으로 변환
        FarmProduct.QualityGrade qualityGrade = FarmProduct.QualityGrade.valueOf(requestDto.getQualityGrade());

        // 3. 엔티티 생성
        FarmProduct product = FarmProduct.builder()
                .store(store)
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .qualityGrade(qualityGrade)
                .recommendedPrice(requestDto.getRecommendedPrice())
                .stock(requestDto.getStock())
                .build();

        // 4. 저장
        farmProductRepository.save(product);

        return ResponseEntity.ok("상품 등록이 완료되었습니다.");
    }



}


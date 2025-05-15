package com.example.sungshinfarm.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FarmProductRequestDto {
    private String name; // 품목명 (자동입력)
    private String qualityGrade; // 품질등급 (자동입력)
    private String description;  // 설명 (사용자 입력)
    private BigDecimal recommendedPrice; // 권장 가격 (자동입력)
    private int stock; // 수량 (사용자 입력)
}

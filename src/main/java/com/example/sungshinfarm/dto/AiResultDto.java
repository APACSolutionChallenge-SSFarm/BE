package com.example.sungshinfarm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드 받는 생성자
public class AiResultDto {
    private String name;
    private String qualityGrade;
    private BigDecimal recommendedPrice;
}



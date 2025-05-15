package com.example.sungshinfarm.dto;

//package com.yourpackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드 받는 생성자
public class AiResultDto {
    private String name;
    private String qualityGrade;
}



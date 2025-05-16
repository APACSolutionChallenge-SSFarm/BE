package com.example.sungshinfarm.controller;

import com.example.sungshinfarm.dto.AiResultDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiResultController {

    @PostMapping("/predict-result")
    public AiResultDto receivePrediction(@RequestBody AiResultDto resultDto) {
        // 여기서는 단순히 받은 데이터를 그대로 반환만 합니다.
        System.out.println("AI가 보낸 데이터: " + resultDto);
        return resultDto;
    }
}


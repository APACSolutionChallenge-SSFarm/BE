package com.example.sungshinfarm.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDto {
    private String detectedQuality;
    private Float confidence;
    private String imageUrl;

    public UploadResultDto(String imageUrl) {
        this.imageUrl = imageUrl;
        this.detectedQuality = "UNKNOWN";
        this.confidence = 0.0f;
    }
}

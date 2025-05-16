package com.example.sungshinfarm.upload.controller;

import com.example.sungshinfarm.upload.dto.UploadResultDto;
import com.example.sungshinfarm.upload.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping
    public ResponseEntity<UploadResultDto> uploadImage(@RequestParam("image") MultipartFile image) {
        UploadResultDto resultDto = imageUploadService.sendImageToAiAndReturnResult(image);
        return ResponseEntity.ok(resultDto);
    }
}
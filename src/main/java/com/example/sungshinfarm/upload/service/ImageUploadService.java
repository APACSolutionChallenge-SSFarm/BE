package com.example.sungshinfarm.upload.service;

import com.example.sungshinfarm.upload.config.MultipartInputStreamFileResource;
import com.example.sungshinfarm.upload.dto.UploadResultDto;
import com.example.sungshinfarm.upload.entity.ProductImage;
import com.example.sungshinfarm.upload.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final ProductImageRepository productImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeImage(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/uploads/" + fileName;

            // 1. DB에 저장
            productImageRepository.save(ProductImage.builder()
                    .imageUrl(imageUrl)
                    .detectedQuality("UNKNOWN")
                    .confidence(0.0f)
                    .uploadedAt(LocalDateTime.now())
                    .build());

            // 2. AI 서버에 이미지 파일 자체 전송
            sendToAiServer(filePath, fileName);

            return imageUrl;

        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }

    private void sendToAiServer(Path filePath, String fileName) {
        String aiEndpoint = "http://localhost:5000/predict"; // AI 서버 주소

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new MultipartInputStreamFileResource(Files.newInputStream(filePath), fileName));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(aiEndpoint, requestEntity, String.class);
            System.out.println("AI 응답: " + response.getBody());

        } catch (IOException e) {
            System.err.println("AI 전송 실패: " + e.getMessage());
        }
    }

    public UploadResultDto sendImageToAiAndReturnResult(MultipartFile image) {
        String imageUrl = storeImage(image); // 저장 + AI 전송
        return new UploadResultDto(imageUrl);
    }
}

/*@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final ProductImageRepository productImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeImage(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/uploads/" + fileName;

            // 1. DB에 저장
            ProductImage saved = productImageRepository.save(ProductImage.builder()
                    .imageUrl(imageUrl)
                    .detectedQuality("UNKNOWN") // AI 결과 기다림
                    .confidence(0.0f)
                    .uploadedAt(LocalDateTime.now())
                    .build());

            // 2. AI 서버에 이미지 URL 전달
            sendToAiServer(imageUrl);

            return imageUrl;

        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }

    private void sendToAiServer(String imageUrl) {
        String aiEndpoint = "http://localhost:5000/analyze"; // AI 서버 주소

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> body = new HashMap<>();
        body.put("imageUrl", imageUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(aiEndpoint, request, String.class);
            System.out.println("AI 응답: " + response.getBody());
        } catch (Exception e) {
            System.err.println("AI 전송 실패: " + e.getMessage());
        }
    }
}
*/

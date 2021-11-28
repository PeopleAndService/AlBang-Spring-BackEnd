package com.pns.albangwas.api;

import com.pns.albangwas.api.dto.landmark.LandmarkGetResponseDto;
import com.pns.albangwas.api.dto.landmark.LandmarkNormalResponseDto;
import com.pns.albangwas.api.dto.landmark.LandmarkPostRequestDto;
import com.pns.albangwas.domain.landmark.Landmark;
import com.pns.albangwas.domain.landmark.LandmarkState;
import com.pns.albangwas.exception.FileUploadException;
import com.pns.albangwas.service.FileService;
import com.pns.albangwas.service.LandmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class LandmarkApiController {

    private final LandmarkService landmarkService;
    private final FileService fileUploadService;

    @PostMapping("/landmark/new")
    public LandmarkNormalResponseDto saveLandmark(
            @RequestPart(name = "image") MultipartFile landmarkImg,
            @RequestPart(name = "body") LandmarkPostRequestDto landmarkPostRequestDto
    ) {
        if (landmarkImg.isEmpty()) {
            throw new FileUploadException("파일이 존재하지 않습니다.");
        }

        String imagePath = fileUploadService.storeFile(landmarkImg);

        return new LandmarkNormalResponseDto(
                landmarkService.saveLandmark(landmarkPostRequestDto.toEntity(imagePath, LandmarkState.ACTIVE))
        );
    }

    @PostMapping("/landmark/application")
    public LandmarkNormalResponseDto applyLandmark(
            @RequestPart(name = "image") MultipartFile landmarkImg,
            @RequestPart(name = "body") LandmarkPostRequestDto landmarkPostRequestDto
    ) {
        if (landmarkImg.isEmpty()) {
            throw new FileUploadException("파일이 존재하지 않습니다.");
        }

        String imagePath = fileUploadService.storeFile(landmarkImg);

        return new LandmarkNormalResponseDto(
                landmarkService.applyLandmark(landmarkPostRequestDto.toEntity(imagePath, LandmarkState.REGISTER))
        );
    }

    @GetMapping("/landmark/get")
    public LandmarkGetResponseDto getLandmarks() {
        List<Landmark> landmarks = landmarkService.getAllLandmarks();

        return new LandmarkGetResponseDto(
                landmarks.stream().map(LandmarkNormalResponseDto::new).collect(Collectors.toList())
        );
    }

    @GetMapping("/file/get/{fileName:.+}")
    public ResponseEntity<Resource> downloadLandmarkImg(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileUploadService.loadFile(fileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            return null;
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

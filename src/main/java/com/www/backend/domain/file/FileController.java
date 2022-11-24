package com.www.backend.domain.file;

import com.www.backend.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<SuccessResponse> upload(
            @RequestPart("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(fileService.save(multipartFile));
    }

}

package com.www.backend.domain.file;

import com.www.backend.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
@CrossOrigin(origins = "http://localhost:5173, http://localhost:3000, http://localhost:3001, https://wwweb.kr, https://m.wwweb.kr")
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<SuccessResponse> upload(
            @RequestPart("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(fileService.uploadAsset(multipartFile));
    }

    @PostMapping("/multiple")
    public ResponseEntity<SuccessResponse> multipleUpload(
            @RequestHeader String code,
            @RequestPart("files") List<MultipartFile> multipartFiles) {
        return ResponseEntity.ok(fileService.uploadAssets(code, multipartFiles));
    }
}

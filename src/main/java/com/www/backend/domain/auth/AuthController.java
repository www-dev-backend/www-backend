package com.www.backend.domain.auth;

import com.www.backend.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000, https://wwweb.kr, https://m.wwweb.kr")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestHeader String code) {
        return ResponseEntity.ok(authService.login(code));
    }
}

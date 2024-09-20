package com.pobluesky.user.controller;

import com.pobluesky.global.security.JwtToken;

import com.pobluesky.user.dto.request.LogInDto;
import com.pobluesky.user.service.SignService;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.pobluesky.global.util.ResponseFactory;
import com.pobluesky.global.util.model.CommonResult;
import com.pobluesky.global.util.model.JsonResult;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Slf4j
public class SignController {

    private final SignService signService;

    @PostMapping("/sign-in")
    @Operation(summary = "로그인")
    public JwtToken signIn(@RequestBody LogInDto logInDto) {
        String email = logInDto.email();
        String password = logInDto.password();

        return signService.signIn(email, password);
    }

    @GetMapping("/token")
    public Long parseToken(@RequestParam("token") String token) {

        return signService.parseToken(token);
    }

    @GetMapping("/health")
    public ResponseEntity<CommonResult> healthCheck() {
        
        return ResponseEntity.ok(ResponseFactory.getSuccessResult());
    }

}

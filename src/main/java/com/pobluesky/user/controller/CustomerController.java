package com.pobluesky.user.controller;

import com.pobluesky.user.dto.request.CustomerCreateRequestDTO;
import com.pobluesky.user.dto.request.CustomerUpdateRequestDTO;
import com.pobluesky.user.dto.response.CustomerResponseDTO;

import com.pobluesky.global.util.ResponseFactory;
import com.pobluesky.global.util.model.CommonResult;
import com.pobluesky.global.util.model.JsonResult;
import com.pobluesky.user.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    // msa 용도 고객 존재 여부 확인 엔드포인트
    @GetMapping("/exists")
    @Operation(summary = "msa 용도 고객 존재 여부 확인 엔드포인트")
    public ResponseEntity<Boolean> customerExists(@RequestParam("userId") Long userId) {
        boolean exists = customerService.existsById(userId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping
    @Operation(summary = "고객사 전체 조회")
    public ResponseEntity<JsonResult> getCustomers() {
        List<CustomerResponseDTO> response = customerService.getCustomers();

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @GetMapping("/without-token/{userId}")
    @Operation(summary = "토큰 없이 고객사 조회")
    public ResponseEntity<JsonResult> getCustomerByIdWithoutToken(
        @PathVariable("userId") Long userId
    ) {
        CustomerResponseDTO response = customerService.getCustomerByIdWithoutToken(userId);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "고객사 조회")
    public ResponseEntity<JsonResult> getCustomerById(
        @RequestHeader("Authorization") String token,
        @PathVariable("userId") Long userId
    ) {
        CustomerResponseDTO response = customerService.getCustomerById(token, userId);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @PostMapping("/sign-up")
    @Operation(summary = "고객사 회원가입")
    public ResponseEntity<JsonResult> signUp(@RequestBody CustomerCreateRequestDTO signUpDto) {
        CustomerResponseDTO response = customerService.signUp(signUpDto);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "고객사 정보 수정")
    public ResponseEntity<JsonResult> updateCustomerById(
        @RequestHeader("Authorization") String token,
        @PathVariable("userId") Long userId,
        @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO
    ) {
        CustomerResponseDTO response = customerService.updateCustomerById(
            token,
            userId,
            customerUpdateRequestDTO
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "고객사 삭제")
    public ResponseEntity<CommonResult> deleteCustomerById(
        @RequestHeader("Authorization") String token,
        @PathVariable("userId") Long userId
    ) {
        customerService.deleteCustomerById(token, userId);

        return ResponseEntity.ok(ResponseFactory.getSuccessResult());
    }
}

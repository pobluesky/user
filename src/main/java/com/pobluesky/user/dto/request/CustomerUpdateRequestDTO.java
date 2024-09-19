package com.pobluesky.user.dto.request;

public record CustomerUpdateRequestDTO(
    String name,
    String email,
    String password,
    String phone,
    String customerCode,
    String customerName
) {
}

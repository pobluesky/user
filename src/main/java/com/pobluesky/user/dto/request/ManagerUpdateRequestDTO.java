package com.pobluesky.user.dto.request;

import com.pobluesky.global.entity.Department;
import com.pobluesky.global.security.UserRole;

public record ManagerUpdateRequestDTO(
    String name,
    String email,
    String password,
    String phone,
    UserRole role,
    Department department
) {
}

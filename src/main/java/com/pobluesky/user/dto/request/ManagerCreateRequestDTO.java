package com.pobluesky.user.dto.request;

import com.pobluesky.user.entity.Manager;
import com.pobluesky.global.entity.Department;
import com.pobluesky.global.security.UserRole;

public record ManagerCreateRequestDTO(
    String name,
    String email,
    String password,
    String phone,
    String empNo,
    UserRole role,
    Department department
) {
    public Manager toManagerEntity(String encodedPassword, String securityRole) {

        return Manager.builder()
            .name(name)
            .email(email)
            .password(encodedPassword)
            .phone(phone)
            .empNo(empNo)
            .role(role)
            .department(department)
            .securityRole(securityRole)
            .build();
    }
}

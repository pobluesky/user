package com.pobluesky.user.dto.response;

import com.pobluesky.user.entity.Manager;
import lombok.Builder;

@Builder
public record MobileManagerResponseDTO(
    Long userId,
    String name,
    String email,
    String phone,
    String empNo,
    String role,
    String department
) {
    public static MobileManagerResponseDTO from(Manager manager) {

        return MobileManagerResponseDTO.builder()
            .userId(manager.getUserId())
            .name(manager.getName())
            .email(manager.getEmail())
            .phone(manager.getPhone())
            .empNo(manager.getEmpNo())
            .role(manager.getRole().getName())
            .department(manager.getDepartment().getName())
            .build();
    }
}

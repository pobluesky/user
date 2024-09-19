package com.pobluesky.user.dto.response;

import com.pobluesky.user.entity.Manager;

import lombok.Builder;

@Builder
public record ManagerSummaryResponseDTO(
    Long userId,
    String name
) {

    public static ManagerSummaryResponseDTO from(Manager manager) {
        if (manager == null) return null;
        else {
            return ManagerSummaryResponseDTO.builder()
                .userId(manager.getUserId())
                .name(manager.getName())
                .build();
        }
    }
}

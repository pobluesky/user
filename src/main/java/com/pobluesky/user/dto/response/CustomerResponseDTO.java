package com.pobluesky.user.dto.response;

import com.pobluesky.user.entity.Customer;

import lombok.Builder;

@Builder
public record CustomerResponseDTO(
    Long userId,
    String name,
    String email,
    String password,
    String phone,
    String customerCode,
    String customerName,
    Boolean isActivated
) {

    public static CustomerResponseDTO from(Customer customer) {

        return CustomerResponseDTO.builder()
            .userId(customer.getUserId())
            .name(customer.getName())
            .email(customer.getEmail())
            .password(customer.getPassword())
            .phone(customer.getPhone())
            .customerCode(customer.getCustomerCode())
            .customerName(customer.getCustomerName())
            .isActivated(customer.getIsActivated())
            .build();
    }
}

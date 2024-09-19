package com.pobluesky.user.dto.request;

import com.pobluesky.user.entity.Customer;

public record CustomerCreateRequestDTO(
    String name,
    String email,
    String password,
    String phone,
    String customerCode,
    String customerName
) {

    public Customer toCustomerEntity(String encodedPassword, String securityRole) {

        return Customer.builder()
            .name(name)
            .email(email)
            .password(encodedPassword)
            .phone(phone)
            .customerCode(customerCode)
            .customerName(customerName)
            .securityRole(securityRole)
            .build();
    }
}

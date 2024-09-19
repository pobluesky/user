package com.pobluesky.user.entity;

import com.pobluesky.global.security.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer extends User {

    private String customerCode;

    private String customerName;

    private String securityRole;

    @Builder
    private Customer(
        String name,
        String email,
        String password,
        String phone,
        String customerCode,
        String customerName,
        String securityRole
    ) {
        // TODO : 빈칸일 경우 validate

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.isActivated = true;
        this.role = UserRole.CUSTOMER;
        this.securityRole = securityRole;
    }

    public void updateCustomer(
        String name,
        String email,
        String password,
        String phone
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(securityRole));
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

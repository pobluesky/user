package com.pobluesky.user.entity;

import com.pobluesky.global.entity.Department;
import com.pobluesky.global.security.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "managers")
public class Manager extends User {

    private String empNo;

    @Enumerated(EnumType.STRING)
    private Department department;

    private String securityRole;

    @Builder
    private Manager(
        String name,
        String email,
        String password,
        String phone,
        String empNo,
        UserRole role,
        Department department,
        String securityRole
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.empNo = empNo;
        this.role = role;
        this.department = department;
        this.isActivated = true;
        this.securityRole = securityRole;
    }

    public void updateManager(
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

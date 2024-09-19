package com.pobluesky.user.entity;

import com.pobluesky.global.BaseEntity;
import com.pobluesky.global.security.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;

import org.springframework.security.core.userdetails.UserDetails;

@Getter
@MappedSuperclass
public abstract class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    protected String name;

    protected String email;

    protected String password;

    protected String phone;

    protected Boolean isActivated;

    @Enumerated(EnumType.STRING)
    protected UserRole role;

    public void deleteUser(){
        this.isActivated = false;
    }
}

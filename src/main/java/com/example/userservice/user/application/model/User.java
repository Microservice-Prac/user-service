package com.example.userservice.user.application.model;

import com.example.userservice.core.util.EncryptedUtil;
import com.example.userservice.user.domain.UserEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private String email;
    private String name;
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .encryptedPwd(encryptPassword(password))
                .userId(generateOrderId())
                .build();
    }

    private String encryptPassword(String plainPassword) {
        return EncryptedUtil.passwordEncoder().encode(plainPassword);
    }

    private String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}

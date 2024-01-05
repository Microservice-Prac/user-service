package com.example.userservice.user.application.mapper;

import com.example.userservice.user.domain.entity.UserEntity;
import com.example.userservice.user.presentation.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity UserCreateDtoRequestToUser(UserCreateDto.Request userCreateDtoRequest) {
        return Optional.ofNullable(userCreateDtoRequest)
                .map(request -> UserEntity.builder()
                        .email(request.getEmail())
                        .name(request.getName())
                        .userId(createUserId())
                        .encryptedPwd(passwordEncoder.encode(userCreateDtoRequest.getPassword()))
                        .build())
                .orElseThrow(() -> new IllegalStateException("userRequest cannot be null"));
    }

    private String createUserId() {
        return UUID.randomUUID().toString();
    }

    public UserCreateDto.Response UserToUserCreateDtoResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(user -> UserCreateDto.Response.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .userId(user.getUserId())
                        .build())
                .orElseThrow(() -> new IllegalStateException("userEntity cannot be null"));
    }
}

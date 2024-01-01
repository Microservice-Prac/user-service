package com.example.userservice.application.mapper;

import com.example.userservice.domain.entity.User;
import com.example.userservice.presentation.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User UserCreateDtoRequestToUser(UserCreateDto.Request userCreateDtoRequest) {
        return Optional.ofNullable(userCreateDtoRequest)
                .map(request -> User.builder()
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

    public UserCreateDto.Response UserToUserCreateDtoResponse(User paramUser) {
        return Optional.ofNullable(paramUser)
                .map(user -> UserCreateDto.Response.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .userId(user.getUserId())
                        .build())
                .orElseThrow(() -> new IllegalStateException("userEntity cannot be null"));
    }
}

package com.example.userservice.application;

import com.example.userservice.domain.entity.User;
import com.example.userservice.presentation.request.UserRequest;
import com.example.userservice.presentation.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserMapper {

    public User userRequestToUser(UserRequest userRequest) {
        return Optional.ofNullable(userRequest)
                .map(request -> User.builder()
                        .email(request.getEmail())
                        .name(request.getName())
                        .userId(UUID.randomUUID().toString())
                        .encryptedPwd("encrypted_password")
                        .build())
                .orElseThrow(() -> new IllegalStateException("userRequest cannot be null"));
    }

    public UserResponse userToUserResponse(User paramUser) {
        return Optional.ofNullable(paramUser)
                .map(user -> UserResponse.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .userId(user.getUserId())
                        .build())
                .orElseThrow(() -> new IllegalStateException("userEntity cannot be null"));
    }
}

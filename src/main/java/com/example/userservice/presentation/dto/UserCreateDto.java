package com.example.userservice.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class UserCreateDto {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {

        @NotNull(message = "Email cannot be null")
        @Size(min = 2, message = "Email not be less than two characters")
        @Email
        private String email;

        @NotNull(message = "Name cannot be null")
        @Size(min = 2, message = "Name not be less than two characters")
        private String name;

        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password must be equal or grater than 8 characters")
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private String email;
        private String name;
        private String userId;
    }
}

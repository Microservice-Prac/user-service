package com.example.userservice.user.presentation.dto;

import com.example.userservice.core.util.EncryptedUtil;
import com.example.userservice.core.util.ModelMapperUtil;
import com.example.userservice.api.dto.Order;
import com.example.userservice.user.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDto {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateRequest {

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

        public UserEntity toEntity() {
            String userId = UUID.randomUUID().toString();
            String encryptedPwd = EncryptedUtil.passwordEncoder().encode(this.password);

            return UserEntity.builder()
                    .email(this.email)
                    .name(this.name)
                    .userId(userId)
                    .encryptedPwd(encryptedPwd)
                    .build();
        }
    }

    @Setter
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private String email;
        private String name;
        private String userId;
        private LocalDateTime createAt;
        private List<Order> orders;

        public static Response of(UserEntity userEntity) {
            return ModelMapperUtil.modelMapper().map(userEntity, Response.class);
        }

        public static List<Response> of(List<UserEntity> userEntityList) {
            return userEntityList.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }
    }
}

package com.example.userservice.presentation.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private String email;
    private String name;
    private String userId;
}

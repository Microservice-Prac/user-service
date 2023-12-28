package com.example.userservice.application;

import com.example.userservice.domain.entity.User;
import com.example.userservice.infrastructure.UserRepository;
import com.example.userservice.presentation.request.UserRequest;
import com.example.userservice.presentation.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);
        userRepository.save(user);
        UserResponse userResponse = userMapper.userToUserResponse(user);

        return userResponse;
    }
}

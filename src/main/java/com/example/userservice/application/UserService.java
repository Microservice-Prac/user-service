package com.example.userservice.application;

import com.example.userservice.application.mapper.UserMapper;
import com.example.userservice.domain.entity.User;
import com.example.userservice.infrastructure.UserRepository;
import com.example.userservice.presentation.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserCreateDto.Response createUser(UserCreateDto.Request userCreateDtoRequest) {
        User user = userMapper.UserCreateDtoRequestToUser(userCreateDtoRequest);
        userRepository.save(user);
        UserCreateDto.Response userCreateDtoResponse = userMapper.UserToUserCreateDtoResponse(user);

        return userCreateDtoResponse;
    }
}

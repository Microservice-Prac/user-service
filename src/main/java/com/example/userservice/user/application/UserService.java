package com.example.userservice.user.application;

import com.example.userservice.user.application.mapper.UserMapper;
import com.example.userservice.user.domain.entity.UserEntity;
import com.example.userservice.user.infrastructure.UserRepository;
import com.example.userservice.user.presentation.dto.UserCreateDto;
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
        UserEntity userEntity = userMapper.UserCreateDtoRequestToUser(userCreateDtoRequest);
        userRepository.save(userEntity);
        UserCreateDto.Response userCreateDtoResponse = userMapper.UserToUserCreateDtoResponse(userEntity);

        return userCreateDtoResponse;
    }
}

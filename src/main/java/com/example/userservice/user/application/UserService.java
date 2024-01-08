package com.example.userservice.user.application;

import com.example.userservice.user.domain.entity.UserEntity;
import com.example.userservice.user.infrastructure.UserRepository;
import com.example.userservice.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserDto.CreateRequest userDtoCreateRequest) {
        UserEntity userEntity = userDtoCreateRequest.toEntity();
        userRepository.save(userEntity);
    }
}

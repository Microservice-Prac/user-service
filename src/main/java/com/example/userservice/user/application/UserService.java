package com.example.userservice.user.application;

import com.example.userservice.user.domain.entity.UserEntity;
import com.example.userservice.user.infrastructure.UserRepository;
import com.example.userservice.core.vo.Order;
import com.example.userservice.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public UserDto.Response getUserById(String userId) {
        UserEntity userEntity = Optional.ofNullable(userRepository.findByUserId(userId))
                .orElseThrow(() -> new UsernameNotFoundException("해당 userId에 해당하는 사용자를 찾을 수 없습니다."));

        List<Order> orders = new ArrayList<>();
        userEntity.saveUserOrders(orders);

        return UserDto.Response.of(userEntity);
    }

    public List<UserDto.Response> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();

        return UserDto.Response.of(userEntityList);
    }
}

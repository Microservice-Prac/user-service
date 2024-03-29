package com.example.userservice.user.presentation;

import com.example.userservice.user.application.UserService;
import com.example.userservice.user.presentation.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment environment;

    @GetMapping("/health_check")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok(String.format("It's Working in User Service on PORT %s", environment.getProperty("local.server.port")));
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok(environment.getProperty("greeting.message"));
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto.CreateRequest userDtoCreateRequest) {
        userService.createUser(userDtoCreateRequest.toModel());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto.Response>> getAllUsers() {
        List<UserDto.Response> userList = userService.getAllUsers();

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto.Response> getUserById(@PathVariable String userId) {
        UserDto.Response user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }
}
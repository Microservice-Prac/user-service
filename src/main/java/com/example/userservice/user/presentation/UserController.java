package com.example.userservice.user.presentation;

import com.example.userservice.user.application.UserService;
import com.example.userservice.user.presentation.dto.UserCreateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateDto.Request userCreateDtoRequest) {
        UserCreateDto.Response userCreateDtoResponse = userService.createUser(userCreateDtoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateDtoResponse);
    }
}

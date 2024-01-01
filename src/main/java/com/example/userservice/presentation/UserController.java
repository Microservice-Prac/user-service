package com.example.userservice.presentation;

import com.example.userservice.application.UserService;
import com.example.userservice.presentation.dto.UserCreateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment environment;

    @GetMapping("/health_check")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("It's Working in User Service");
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
       return ResponseEntity.ok(environment.getProperty("greeting.message"));
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateDto.Request userCreateDtoRequest) {
        UserCreateDto.Response userCreateDtoResponse = userService.createUser(userCreateDtoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateDtoResponse);
    }
}

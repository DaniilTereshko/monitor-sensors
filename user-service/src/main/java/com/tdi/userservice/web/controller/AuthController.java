package com.tdi.userservice.web.controller;

import com.tdi.userservice.model.UserRole;
import com.tdi.userservice.security.SecurityUser;
import com.tdi.userservice.service.AuthService;
import com.tdi.userservice.web.dto.LoginRequestDto;
import com.tdi.userservice.web.dto.LoginResponseDto;
import com.tdi.userservice.web.dto.RegistrationRequestDto;
import com.tdi.userservice.web.dto.UserDto;
import com.tdi.userservice.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Аутентификация", description = "Контроллер для операций регистрации и аутентификации пользователя")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать нового пользователя в системе"
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Validated final RegistrationRequestDto dto) {
        var user = userMapper.fromDto(dto);
        authService.register(user);
    }

    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет пользователю войти в систему, получив токены доступа и обновления"
    )
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Validated final LoginRequestDto dto) {
        return authService.login(dto);
    }

    @Operation(
            summary = "Обновление токенов",
            description = "Позволяет обновить токены доступа и обновления, используя refresh токен"
    )
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponseDto> refreshToken(
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        return ResponseEntity.ok(authService.refreshToken(req, resp));
    }

    @Hidden
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        var userDto = authService.getMe();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
package com.tdi.userservice.service.impl;

import com.tdi.userservice.common.exception.ResourceAlreadyExistsException;
import com.tdi.userservice.common.exception.UnauthorizedAccessException;
import com.tdi.userservice.model.User;
import com.tdi.userservice.model.UserRole;
import com.tdi.userservice.security.SecurityUser;
import com.tdi.userservice.security.jwt.JwtType;
import com.tdi.userservice.security.service.JwtService;
import com.tdi.userservice.security.service.UserHolder;
import com.tdi.userservice.service.AuthService;
import com.tdi.userservice.service.UserService;
import com.tdi.userservice.web.dto.LoginRequestDto;
import com.tdi.userservice.web.dto.LoginResponseDto;
import com.tdi.userservice.web.dto.UserDto;
import com.tdi.userservice.web.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.tdi.userservice.common.util.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserHolder userHolder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(User user) {
        if (userService.existsByUsername(user.getUsername())) {
            throw new ResourceAlreadyExistsException(RESOURCE_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.VIEWER);

        userService.create(user);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        final var username = request.getUsername();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    request.getPassword()
            ));
        } catch (AuthenticationException ex) {
            throw new UnauthorizedAccessException(INVALID_CREDENTIALS);
        }

        return LoginResponseDto.builder()
                .access(jwtService.generateToken(username, JwtType.ACCESS))
                .refresh(jwtService.generateToken(username, JwtType.REFRESH))
                .build();
    }

    @Override
    public LoginResponseDto refreshToken(HttpServletRequest req, HttpServletResponse resp) {
        final var authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (!jwtService.isValidaAuthHeader(authHeader)) {
            throw new UnauthorizedAccessException(INVALID_AUTH_HEADER);
        }

        final var jwt = authHeader.substring(7);

        final var username = jwtService.getSubject(jwt);
        userService.existsByUsername(username);

        if (!jwtService.isValid(jwt, JwtType.REFRESH)) {
            throw new UnauthorizedAccessException(INVALID_REFRESH_TOKEN);
        }

        return LoginResponseDto.builder()
                .access(jwtService.generateToken(username, JwtType.ACCESS))
                .refresh(jwtService.generateToken(username, JwtType.REFRESH))
                .build();
    }

    @Override
    public UserDto getMe() {
        var userDetails = userHolder.getUser();
        var user = userService.getByUsername(userDetails.getUsername());
        return userMapper.toDto(user);
    }

}
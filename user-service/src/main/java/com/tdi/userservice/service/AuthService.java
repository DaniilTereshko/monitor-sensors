package com.tdi.userservice.service;

import com.tdi.userservice.model.User;
import com.tdi.userservice.model.UserRole;
import com.tdi.userservice.security.SecurityUser;
import com.tdi.userservice.web.dto.LoginRequestDto;
import com.tdi.userservice.web.dto.LoginResponseDto;
import com.tdi.userservice.web.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    void register(User user);

    LoginResponseDto login(LoginRequestDto request);

    LoginResponseDto refreshToken(HttpServletRequest req, HttpServletResponse resp);

    UserDto getMe();

}
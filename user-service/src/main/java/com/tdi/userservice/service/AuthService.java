package com.tdi.userservice.service;

import com.tdi.userservice.model.User;
import com.tdi.userservice.web.dto.LoginRequestDto;
import com.tdi.userservice.web.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void register(User user);

    LoginResponseDto login(LoginRequestDto request);

    LoginResponseDto refreshToken(HttpServletRequest req, HttpServletResponse resp);

}
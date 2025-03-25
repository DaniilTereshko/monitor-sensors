package com.tdi.userservice.security.service;

import com.tdi.userservice.security.SecurityUser;
import com.tdi.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final UserService userService;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        var user = userService.getByUsername(username);
        return new SecurityUser(user);
    }

}
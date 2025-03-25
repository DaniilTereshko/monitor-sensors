package com.tdi.userservice.service;

import com.tdi.userservice.model.User;

public interface UserService {

    boolean existsByUsername(String username);

    User getByUsername(String username);

    void create(User user);

}
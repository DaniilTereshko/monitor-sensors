package com.tdi.userservice.service.impl;

import com.tdi.userservice.model.User;
import com.tdi.userservice.repository.UserRepository;
import com.tdi.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tdi.userservice.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.tdi.userservice.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ATTRIBUTE;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ATTRIBUTE, User.class.getSimpleName(), "username", username));
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }

}
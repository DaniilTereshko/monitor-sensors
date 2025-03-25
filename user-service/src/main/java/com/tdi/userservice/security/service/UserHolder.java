package com.tdi.userservice.security.service;

import com.tdi.userservice.security.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public SecurityUser getUser(){
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
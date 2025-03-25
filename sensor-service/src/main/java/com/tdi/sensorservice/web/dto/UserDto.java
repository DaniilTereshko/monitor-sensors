package com.tdi.sensorservice.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private String name;
    private String username;
    private UserRole role;

}
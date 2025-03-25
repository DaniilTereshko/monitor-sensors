package com.tdi.userservice.web.mapper;

import com.tdi.userservice.model.User;
import com.tdi.userservice.security.SecurityUser;
import com.tdi.userservice.web.dto.RegistrationRequestDto;
import com.tdi.userservice.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.lang.annotation.Target;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User fromDto(RegistrationRequestDto dto);

    UserDto toDto(User user);

}
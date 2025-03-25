package com.tdi.userservice.web.mapper;

import com.tdi.userservice.model.User;
import com.tdi.userservice.web.dto.RegistrationRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User fromDto(RegistrationRequestDto dto);

}
package com.tdi.sensorservice.service.client;

import com.tdi.sensorservice.web.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserServiceClient {

    @GetMapping("/api/v1/auth/me")
    ResponseEntity<UserDto> getMe(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt);

}
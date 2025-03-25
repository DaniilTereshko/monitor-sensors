package com.tdi.userservice.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Ответ, содержащий токены доступа и обновления")
public class LoginResponseDto {

    @Schema(description = "Токен доступа для аутентификации", example = "eyJhbGciOiJIUzI1...")
    private String access;

    @Schema(description = "Токен обновления для получения новых токенов доступа", example = "dGhpcyBpcyBhIHJlZnJlc2g...")
    private String refresh;

}
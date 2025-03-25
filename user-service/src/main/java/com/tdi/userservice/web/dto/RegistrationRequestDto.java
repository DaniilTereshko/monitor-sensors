package com.tdi.userservice.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.tdi.userservice.common.util.ValidationMessage.*;

@Getter
@Setter
@Schema(description = "Запрос на регистрацию пользователя")
public class RegistrationRequestDto {

    @NotBlank(message = NAME_FIELD_IS_EMPTY)
    @Size(min = 1, max = 255, message = NAME_INCORRECT_LENGTH)
    @Schema(
            description = "Имя пользователя",
            example = "Иван",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotBlank(message = USERNAME_FIELD_IS_EMPTY)
    @Size(min = 1, max = 255, message = USERNAME_INCORRECT_LENGTH)
    @Schema(
            description = "Имя пользователя",
            example = "user123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String username;

    @NotBlank(message = PASSWORD_FIELD_IS_EMPTY)
    @Size(min = 6, message = PASSWORD_INCORRECT_LENGTH)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(
            description = "Пароль пользователя",
            example = "securePassword123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;
}
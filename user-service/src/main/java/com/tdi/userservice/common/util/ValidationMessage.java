package com.tdi.userservice.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessage {

    /* RegistrationRequestDto  */
    public static final String NAME_INCORRECT_LENGTH = "validation.registration.name.incorrect_length";
    public static final String NAME_FIELD_IS_EMPTY = "validation.registration.name.empty";
    public static final String USERNAME_INCORRECT_LENGTH = "validation.registration.username.incorrect_length";
    public static final String USERNAME_FIELD_IS_EMPTY = "validation.registration.username.empty";
    public static final String PASSWORD_INCORRECT_LENGTH = "validation.registration.password.incorrect_length";
    public static final String PASSWORD_FIELD_IS_EMPTY = "validation.registration.password.empty";

    /* LoginRequestDto */
    public static final String LOGIN_USERNAME_EMPTY = "validation.login.username.empty";
    public static final String LOGIN_PASSWORD_EMPTY = "validation.login.password.empty";

}
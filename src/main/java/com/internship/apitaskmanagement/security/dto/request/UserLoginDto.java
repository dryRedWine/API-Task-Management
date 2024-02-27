package com.internship.apitaskmanagement.security.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Форма для авторизации пользователя в системе")
public class UserLoginDto {

    @NotNull
    @Email
    @Schema(description = "Электронная почта")
    private String email;

    @NotBlank
    @Size(min = 6, max = 64)
    @Schema(description = "Пароль для входа в систему")
    private String password;

    public UserLoginDto() {
    }

}
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
@Schema(description = "Форма для регистрации пользователя в системе")
public class UserRegistrationDto {

    @NotNull
    @Size(min = 2, max = 64)
    @Schema(description = "Полное имя")
    private String fullname;

    @NotBlank
    @Size(min = 2, max = 30)
    @Schema(description = "Уникальное имя пользователя")
    private String username;

    @NotNull
    @Email
    @Schema(description = "Электронная почта")
    private String email;

    @NotBlank
    @Size(min = 6, max = 64)
    @Schema(description = "Пароль")
    private String password;

    public UserRegistrationDto(){
    }

}
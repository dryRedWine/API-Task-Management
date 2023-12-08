package com.internship.apitaskmanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginDto {

    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 64)
    private String password;

    public UserLoginDto() {
    }

}
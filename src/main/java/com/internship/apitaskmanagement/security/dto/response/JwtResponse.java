package com.internship.apitaskmanagement.security.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Форма для вывода ответа про jwt token")
public record JwtResponse(@Schema(description = "id") Long id,
                          @Schema(description = "Имя пользователя") String username,
                          @Schema(description = "Электронная почта") String email,
                          @Schema(description = "Роли пользователя") Set<String> roles,
                          @Schema(description = "Токен доступа") String accessToken) {
}

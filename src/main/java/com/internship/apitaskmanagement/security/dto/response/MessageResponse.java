package com.internship.apitaskmanagement.security.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Форма для вывода сообщения об ошибки при аутентификации")
public record MessageResponse(@Schema(description = "Сообщение об ошибке") String message) {}

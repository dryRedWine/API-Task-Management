package com.internship.apitaskmanagement.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "DTO для обработки переданных данных о новой задаче")
public record TaskCreateDto(@Schema(description = "Заголовок") @NotNull String heading,
                            @Schema(description = "Описание") @NotNull String description,
                            @Schema(description = "Дедлайн")
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deadline,
                            @Schema(description = "Статус") @NotNull Status status,
                            @Schema(description = "Приоритет") @NotNull Priority priority,
                            @Schema(description = "Имена исполнителей") @NotNull @NotEmpty Set<String> usernames) {
}
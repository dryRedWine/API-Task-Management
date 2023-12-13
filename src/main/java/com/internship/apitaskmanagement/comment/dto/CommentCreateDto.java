package com.internship.apitaskmanagement.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "DTO для обработки данных о создании нового комментария")
public record CommentCreateDto(@Schema(description = "Идентификатор задачи") @NotNull @Positive Long taskId,
                               @Schema(description = "Текст комментария") @NotNull String text) {
}


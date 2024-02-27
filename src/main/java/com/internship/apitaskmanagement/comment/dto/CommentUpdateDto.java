package com.internship.apitaskmanagement.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO для обновления комментария")
public record CommentUpdateDto(@Schema(description = "Текст комментария") @NotBlank String text) {
}

package com.internship.apitaskmanagement.comment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record CommentCreateDto(@NotNull @Positive Long taskId, @NotNull String text) {
}


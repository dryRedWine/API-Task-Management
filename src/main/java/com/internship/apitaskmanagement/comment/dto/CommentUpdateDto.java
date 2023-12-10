package com.internship.apitaskmanagement.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDto(@NotBlank String text) {
}

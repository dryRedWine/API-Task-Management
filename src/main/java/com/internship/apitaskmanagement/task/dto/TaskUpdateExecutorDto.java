package com.internship.apitaskmanagement.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


@Schema(description = "DTO для добавления или удаления исполнителей автором задачи")
public record TaskUpdateExecutorDto(@Schema(description = "Исполнители задачи") @NotNull Set<String> usernames) {
}

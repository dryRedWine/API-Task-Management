package com.internship.apitaskmanagement.task.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

// DTO для добавления или удаления исполнителей автором задачи
public record TaskUpdateExecutorDto(@NotNull Set<String> usernames) {
}

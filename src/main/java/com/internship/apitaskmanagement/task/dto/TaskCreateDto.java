package com.internship.apitaskmanagement.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;


public record TaskCreateDto(@NotNull String heading,
                            @NotNull String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deadline,
                            @NotNull Status status,
                            @NotNull Priority priority,
                            @NotNull @NotEmpty Set<String> usernames) {
}
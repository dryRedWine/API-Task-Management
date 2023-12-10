package com.internship.apitaskmanagement.task.dto;

import com.internship.apitaskmanagement.enums.Status;

// DTO для смены статуса исполнителем
public record TaskStatusChangeDto(Status status) {
}

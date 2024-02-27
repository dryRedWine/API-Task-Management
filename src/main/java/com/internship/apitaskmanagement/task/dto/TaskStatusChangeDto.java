package com.internship.apitaskmanagement.task.dto;

import com.internship.apitaskmanagement.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "DTO для смены статуса исполнителем")
public record TaskStatusChangeDto(@Schema(description = "Статус") Status status) {
}

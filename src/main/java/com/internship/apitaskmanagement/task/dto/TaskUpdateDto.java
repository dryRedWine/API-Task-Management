package com.internship.apitaskmanagement.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO для обновления задачи")
public record TaskUpdateDto(@Schema(description = "Заголовок") String heading,
                            @Schema(description = "Описание") String description,
                            @Schema(description = "Дедлайн")
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deadline,
                            @Schema(description = "Статус") Status status,
                            @Schema(description = "Приоритет") Priority priority) {

}

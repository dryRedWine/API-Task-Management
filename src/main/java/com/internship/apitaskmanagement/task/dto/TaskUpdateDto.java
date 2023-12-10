package com.internship.apitaskmanagement.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;

import java.time.LocalDateTime;

public record TaskUpdateDto(String heading,
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deadline,
                            Status status,
                            Priority priority) {

}

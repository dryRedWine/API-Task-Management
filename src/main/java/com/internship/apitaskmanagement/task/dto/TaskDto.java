package com.internship.apitaskmanagement.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import com.internship.apitaskmanagement.user.UserShortDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@ToString
public class TaskDto {

    @NotNull
    private Long id;

    @NotNull
    private String heading;

    @NotNull
    private String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;

    @NotNull
    private Status status;

    @NotNull
    private Priority priority;

    @NotNull
    private UserShortDto author;

    @NotNull
    private Set<UserShortDto> executors;

}

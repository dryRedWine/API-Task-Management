package com.internship.apitaskmanagement.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.apitaskmanagement.comment.Comment;
import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import com.internship.apitaskmanagement.user.UserShortDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@ToString
@Schema(description = "DTO для вывода информации о задаче")
@EqualsAndHashCode
public class TaskDto {

    @NotNull
    @Schema(description = "Идентификатор задачи")
    private Long id;

    @NotNull
    @Schema(description = "Заголовок")
    private String heading;

    @NotNull
    @Schema(description = "Описание")
    private String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дата создания задачи")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дедлайн (опционально)")
    private LocalDateTime deadline;

    @NotNull
    @Schema(description = "Статус")
    private Status status;

    @NotNull
    @Schema(description = "Приоритет")
    private Priority priority;

    @NotNull
    @Schema(description = "Автор")
    private UserShortDto author;

    @NotNull
    @Schema(description = "Исполнители")
    private Set<UserShortDto> executors;

    @NotNull
    @Schema(description = "Комментарии")
    private Set<Comment> comments;

}

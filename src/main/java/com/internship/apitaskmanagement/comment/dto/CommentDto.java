package com.internship.apitaskmanagement.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO для вывода информации о комментарии")
public class CommentDto {

    @NotNull
    @Schema(description = "Идентификатор пользователя")
    private Long id;

    @NotBlank
    @Schema(description = "Текст комментария")
    private String text;

    @NotNull
    @Schema(description = "Имя пользователя")
    private String username;

    @NotNull
    @Schema(description = "Идентификатор задачи")
    private Long taskId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Время создания комментария")
    private LocalDateTime written;

    @BooleanFlag
    @Schema(description = "Информация изменен комментарий или нет")
    private Boolean changed;
}

package com.internship.apitaskmanagement.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CommentDto {

    @NotNull
    private Long id;

    @NotBlank
    private String text;

    @NotNull
    private String username;

    @NotNull
    private Long taskId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime written;

    @BooleanFlag
    private Boolean changed;
}

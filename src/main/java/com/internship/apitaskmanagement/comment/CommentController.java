package com.internship.apitaskmanagement.comment;

import com.internship.apitaskmanagement.comment.dto.CommentCreateDto;
import com.internship.apitaskmanagement.comment.dto.CommentDto;
import com.internship.apitaskmanagement.comment.dto.CommentUpdateDto;
import com.internship.apitaskmanagement.comment.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api-task-management/pool")
@Tag(name = "Комментарии", description = "Контроллеры для работы с комментариями")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{username}/tasks/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ONLY_READING')")
    @Operation(description = "Создание комментария")
    public CommentDto create(@Parameter(description = "Имя пользователя") @PathVariable @NotBlank String username,
                             @RequestBody CommentCreateDto newComment) {
        log.info("Create comment by user({}) to event {}", username, newComment.taskId());
        return commentService.create(username, newComment);
    }

    @PatchMapping("/{username}/tasks/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ONLY_READING')")
    @Operation(description = "Обновление задачи")
    public CommentDto change(@Parameter(description = "Имя пользователя") @PathVariable @NotBlank String username,
                             @Parameter(description = "Идентификатор комментария") @PathVariable @Positive Long commentId,
                             @RequestBody CommentUpdateDto commentUpdateDto) {
        log.info("Change comment({}) by user({})", commentId, username);
        return commentService.change(username, commentId, commentUpdateDto);
    }

    @DeleteMapping("/{username}/tasks/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ONLY_READING')")
    @Operation(description = "Удаления комментария")
    public void delete(@Parameter(description = "Имя пользователя") @PathVariable @NotBlank String username,
                       @Parameter(description = "Идентификатор комментария") @PathVariable @Positive Long commentId) {
        log.info("Delete comment({}) by user({})", commentId, username);
        commentService.delete(username, commentId);
    }

}

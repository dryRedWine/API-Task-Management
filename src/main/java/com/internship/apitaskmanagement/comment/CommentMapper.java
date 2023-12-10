package com.internship.apitaskmanagement.comment;

import com.internship.apitaskmanagement.comment.dto.CommentCreateDto;
import com.internship.apitaskmanagement.comment.dto.CommentDto;
import com.internship.apitaskmanagement.task.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommentMapper {

    public static Comment toComment(CommentCreateDto commentCreateDto, Task task, String username) {
        return Comment.builder()
                .text(commentCreateDto.text())
                .writerUsername(username)
                .task(task)
                .written(LocalDateTime.now())
                .build();
    }

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .username(comment.getWriterUsername())
                .written(comment.getWritten())
                .taskId(comment.getTask().getId())
                .changed(comment.isChanged())
                .build();
    }
}

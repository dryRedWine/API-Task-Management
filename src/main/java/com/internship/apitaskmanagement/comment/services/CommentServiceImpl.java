package com.internship.apitaskmanagement.comment.services;

import com.internship.apitaskmanagement.comment.Comment;
import com.internship.apitaskmanagement.comment.CommentMapper;
import com.internship.apitaskmanagement.comment.CommentRepository;
import com.internship.apitaskmanagement.comment.dto.CommentCreateDto;
import com.internship.apitaskmanagement.comment.dto.CommentDto;
import com.internship.apitaskmanagement.comment.dto.CommentUpdateDto;
import com.internship.apitaskmanagement.error.exceptions.CommentNotFoundException;
import com.internship.apitaskmanagement.error.exceptions.RangeTimeException;
import com.internship.apitaskmanagement.error.exceptions.TaskNotFoundException;
import com.internship.apitaskmanagement.error.exceptions.UserNotFoundException;
import com.internship.apitaskmanagement.task.Task;
import com.internship.apitaskmanagement.task.TaskRepository;
import com.internship.apitaskmanagement.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    @Override
    public CommentDto create(String username, CommentCreateDto newComment) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("Пользователя с таким username(" + username + ") не существует");
        Task task = taskRepository.findById(newComment.taskId())
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким taskId(" + newComment.taskId() + ") не существует"));
        Comment result = commentRepository.save(CommentMapper.toComment(newComment, task, username));
        return CommentMapper.toCommentDto(result);
    }

    @Override
    public CommentDto change(String username, Long commentId, CommentUpdateDto commentUpdateDto) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("Пользователя с таким username(" + username + ") не существует");
        if (!commentRepository.existsByIdAndWriterUsername(commentId, username))
            throw new IllegalArgumentException(
                    "Комментария с параметрами id=" + commentId + " и username=" + username + " не существует.");
        if (!commentRepository.timeCheck(commentId, LocalDateTime.now().minusHours(1)))
            throw new RangeTimeException("Отредактировать комментарий можно не позднее, чем через час после публикации.");

        // Смена значений в бд
        commentRepository.setNewText(commentUpdateDto.text(), commentId);
        commentRepository.setChangedStatusIsTrue(commentId);

        return CommentMapper.toCommentDto(commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий не найден")));
    }

    @Override
    public void delete(String username, Long commentId) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("Пользователя с таким username(" + username + ") не существует");
        if (!commentRepository.existsByIdAndWriterUsername(commentId, username))
            throw new IllegalArgumentException(
                    "Комментария с параметрами id=" + commentId + " и username=" + username + " не существует.");
        commentRepository.deleteById(commentId);
    }
}

package com.internship.apitaskmanagement.task.services.impl;

import com.internship.apitaskmanagement.error.exceptions.RangeTimeException;
import com.internship.apitaskmanagement.error.exceptions.TaskNotFoundException;
import com.internship.apitaskmanagement.error.exceptions.UserAccessException;
import com.internship.apitaskmanagement.error.exceptions.UserNotFoundException;
import com.internship.apitaskmanagement.task.Task;
import com.internship.apitaskmanagement.task.TaskMapper;
import com.internship.apitaskmanagement.task.TaskRepository;
import com.internship.apitaskmanagement.task.TaskSort;
import com.internship.apitaskmanagement.task.dto.*;
import com.internship.apitaskmanagement.task.services.TaskService;
import com.internship.apitaskmanagement.user.models.User;
import com.internship.apitaskmanagement.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @Override
    public TaskDto create(TaskCreateDto taskCreateDto, String username) {
        LocalDateTime now = LocalDateTime.now();
        if (taskCreateDto.deadline() != null) {
            if (now.isAfter(taskCreateDto.deadline()))
                throw new RangeTimeException("Дедлайн не может быть раньше создания задачи");
        }

        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "Пользователя с таким username(" + username + ") не существует"));

        Task result = TaskMapper.toTaskCreate(taskCreateDto, now, author, getExecutorsFromUsernames(taskCreateDto.usernames()));
        return TaskMapper.toTaskDto(taskRepository.save(result));
    }

    @Override
    public TaskDto change(String username, Long taskId, TaskUpdateDto taskUpdateDto) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("Пользователя с таким username(" + username + ") не существует");
        if (!taskRepository.existsTaskByIdAndAuthorUsername(taskId, username))
            throw new UserAccessException("Только автор задачи может изменять ее");
        Task toUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким takId(" + taskId + ") не существует"));

        // Обновление
        if (taskUpdateDto.heading() != null) toUpdate.setHeading(taskUpdateDto.heading());
        if (taskUpdateDto.description() != null) toUpdate.setDescription(taskUpdateDto.description());
        if (taskUpdateDto.priority() != null) toUpdate.setPriority(taskUpdateDto.priority());
        if (taskUpdateDto.status() != null) toUpdate.setStatus(taskUpdateDto.status());
        if (taskUpdateDto.deadline() != null) {
            if (LocalDateTime.now().isAfter(taskUpdateDto.deadline()))
                throw new RangeTimeException("Дедлайн не может быть закончиться не начавшись");
            else
                toUpdate.setDeadline(taskUpdateDto.deadline());
        }

        return TaskMapper.toTaskDto(taskRepository.save(toUpdate));
    }

    private Set<User> getExecutorsFromUsernames(Set<String> usernames) {
        final Set<User> executors = new HashSet<>();
        User temp;
        for (String executor : usernames) {
            temp = userRepository.findByUsername(executor)
                    .orElseThrow(() -> new UserNotFoundException(
                            "Пользователя с таким username(" + executor + ") не существует"));
            executors.add(temp);
        }
            return executors;
    }

    @Override
    public TaskDto addExecutor(String username, Long taskId, TaskUpdateExecutorDto taskExecutorDto) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("Пользователя с таким username(" + username + ") не существует");
        if (!taskRepository.existsTaskByIdAndAuthorUsername(taskId, username))
            throw new UserAccessException("Только автор задачи может изменять ее");
        Task toUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким takId(" + taskId + ") не существует"));

        final Set<User> updateExecutors = toUpdate.getExecutors();
        updateExecutors.addAll(getExecutorsFromUsernames(taskExecutorDto.usernames()));
        toUpdate.setExecutors(updateExecutors);

        taskRepository.changeExecutor(taskId, updateExecutors);
        return TaskMapper.toTaskDto(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким takId(" + taskId + ") не существует")));
    }

    @Override
    public TaskDto deleteExecutor(String username, Long taskId, TaskUpdateExecutorDto taskExecutorDto) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("Пользователя с таким username(" + username + ") не существует");
        if (!taskRepository.existsTaskByIdAndAuthorUsername(taskId, username))
            throw new UserAccessException("Только автор задачи может изменять ее");
        Task toUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким takId(" + taskId + ") не существует"));

        final Set<User> updateExecutors = toUpdate.getExecutors();
        updateExecutors.removeAll(getExecutorsFromUsernames(taskExecutorDto.usernames()));
        toUpdate.setExecutors(updateExecutors);

        taskRepository.changeExecutor(taskId, updateExecutors);
        return TaskMapper.toTaskDto(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким takId(" + taskId + ") не существует")));
    }

    @Override
    public void deleteTask(Long taskId, String username) {
        if (!taskRepository.existsTaskByIdAndAuthorUsername(taskId, username))
            throw new UserAccessException("Только автор задачи может удалять ее");
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDto findById(String username, Long taskId) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("Пользователя с таким username(" + username + ") не существует");
        return TaskMapper.toTaskDto(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким takId(" + taskId + ") не существует")));
    }

    @Override
    public TaskDto statusChange(String username, Long taskId, TaskStatusChangeDto taskStatusChangeDto) {
        final User executor = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователя с таким username(" + username + ") не существует"));
        if (!taskRepository.existsTaskByIdAndExecutorsContains(taskId, executor) &&
                !taskRepository.existsTaskByIdAndAuthorUsername(taskId, username))
            throw new UserAccessException("Только автор или исполнитель задачи может менять ее статус");

        taskRepository.changeStatus(taskId, taskStatusChangeDto.status());
        return TaskMapper.toTaskDto(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задачи с таким takId(" + taskId + ") не существует")));
    }

    @Override
    public List<TaskDto> findAllByUser(String username, Integer from, Integer size, String sortOrder) {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователя с таким username(" + username + ") не существует"));
        final Pageable pageable = TaskSort.sortByCreatedDate(from, size, sortOrder);
        return TaskMapper.toTaskDto(taskRepository.findAllByAuthorUsernameOrExecutorsContains(username, user, pageable));
    }
}

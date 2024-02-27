package com.internship.apitaskmanagement.task.services;

import com.internship.apitaskmanagement.task.dto.*;

import java.util.Collection;
import java.util.List;

public interface TaskService {

    TaskDto create(TaskCreateDto taskCreateDto, String username);

    TaskDto change(String username, Long taskId, TaskUpdateDto updateEvent);

    TaskDto addExecutor(String username, Long taskId, TaskUpdateExecutorDto taskExecutorDto);

    TaskDto deleteExecutor(String username, Long taskId, TaskUpdateExecutorDto taskExecutorDto);

    void deleteTask(Long taskId, String username);

    TaskDto findById(String username, Long taskId);

    TaskDto statusChange(String username, Long taskId, TaskStatusChangeDto taskStatusChangeDto);

    List<TaskDto> findAllByUser(String username, Integer from, Integer size, String sortOrder);

}

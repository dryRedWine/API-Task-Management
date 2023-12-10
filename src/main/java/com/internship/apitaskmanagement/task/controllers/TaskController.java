package com.internship.apitaskmanagement.task.controllers;

import com.internship.apitaskmanagement.task.dto.*;
import com.internship.apitaskmanagement.task.services.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api-task-management/pool")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{username}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public TaskDto create(@PathVariable @NotBlank String username, @RequestBody @Valid TaskCreateDto taskCreateDto) {
        log.info("Create task by user({})", username);
        return taskService.create(taskCreateDto, username);
    }

    @PatchMapping("/{username}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public TaskDto change(@PathVariable @NotBlank String username,
                          @PathVariable @Positive Long taskId,
                          @RequestBody TaskUpdateDto updateEvent) {
        log.info("Changed task({}) by user({}) ", taskId, username);
        return taskService.change(username, taskId, updateEvent);
    }

    @DeleteMapping("/{username}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteTask(@PathVariable @NotBlank String username, @PathVariable @Positive Long taskId) {
        log.info("Remove task({}) by user({})", taskId, username);
        taskService.deleteTask(taskId, username);
    }

    @PatchMapping("/{username}/tasks/{taskId}/executors")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public TaskDto addExecutor(@PathVariable @NotBlank String username,
                               @PathVariable @Positive Long taskId,
                               @RequestBody @Valid TaskUpdateExecutorDto taskExecutorDto) {
        log.info("Add executor/s in task({}) by user({}) ", taskId, username);
        return taskService.addExecutor(username, taskId, taskExecutorDto);
    }

    @DeleteMapping("/{username}/tasks/{taskId}/executors")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public TaskDto deleteExecutor(@PathVariable @NotBlank String username,
                                  @PathVariable @Positive Long taskId,
                                  @RequestBody @Valid TaskUpdateExecutorDto taskExecutorDto) {
        log.info("Delete executor/s in task({}) by user({}) ", taskId, username);
        return taskService.deleteExecutor(username, taskId, taskExecutorDto);
    }


    @GetMapping("/{username}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ONLY_READING')")
    public TaskDto findById(@PathVariable @NotBlank String username, @PathVariable @Positive Long taskId) {
        log.info("Get information about task({}) by user({})", taskId, username);
        return taskService.findById(username, taskId);
    }


    @PatchMapping("/{username}/tasks/{taskId}/status")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public TaskDto statusChange(@PathVariable @NotBlank String username,
                                @PathVariable @Positive Long taskId,
                                @RequestBody TaskStatusChangeDto taskStatusChangeDto) {
        log.info("Changed task({}) status by executor/author({}) ", taskId, username);
        return taskService.statusChange(username, taskId, taskStatusChangeDto);
    }

    @GetMapping("/{username}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ONLY_READING')")
    public Collection<TaskDto> findAllByUser(@PathVariable @NotBlank String username,
                                             @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                             @RequestParam(value = "size", defaultValue = "10") @Positive Integer size,
                                             @RequestParam(name = "order", defaultValue = "timeDesc") String sortOrder) {
        log.info("Get information about all tasks by user({})", username);
        return taskService.findAllByUser(username, from, size, sortOrder);
    }


}

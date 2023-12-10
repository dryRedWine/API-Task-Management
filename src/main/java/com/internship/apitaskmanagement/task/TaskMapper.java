package com.internship.apitaskmanagement.task;


import com.internship.apitaskmanagement.task.dto.TaskCreateDto;
import com.internship.apitaskmanagement.task.dto.TaskDto;
import com.internship.apitaskmanagement.user.UserMapper;
import com.internship.apitaskmanagement.user.models.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskMapper {

    public static Task toTask(TaskCreateDto taskCreateDto, LocalDateTime now, User author, Set<User> executors) {
        return Task.builder()
                .heading(taskCreateDto.heading())
                .description(taskCreateDto.description())
                .createdDate(now)
                .deadline(taskCreateDto.deadline())
                .status(taskCreateDto.status())
                .priority(taskCreateDto.priority())
                .author(author)
                .executors(executors)
                .build();
    }

    public static TaskDto toTaskDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .heading(task.getHeading())
                .description(task.getDescription())
                .createdDate(task.getCreatedDate())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(UserMapper.toUserShortDto(task.getAuthor()))
                .executors(UserMapper.toUserShortDto(task.getExecutors()))
                .comments(task.getComments())
                .build();
    }

    public static List<TaskDto> toTaskDto(List<Task> tasks) {
        final List<TaskDto> result = new ArrayList<>();
        for (Task task : tasks)
            result.add(toTaskDto(task));
        return result;
    }
}

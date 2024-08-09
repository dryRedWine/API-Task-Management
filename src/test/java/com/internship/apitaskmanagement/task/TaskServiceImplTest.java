package com.internship.apitaskmanagement.task;

import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import com.internship.apitaskmanagement.error.exceptions.RangeTimeException;
import com.internship.apitaskmanagement.task.dto.TaskCreateDto;
import com.internship.apitaskmanagement.task.dto.TaskDto;
import com.internship.apitaskmanagement.task.services.impl.TaskServiceImpl;
import com.internship.apitaskmanagement.user.UserShortDto;
import com.internship.apitaskmanagement.user.models.User;
import com.internship.apitaskmanagement.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    private TaskServiceImpl taskService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

//    @MockBean
//    private PasswordEncoder encoder;
// 1) verify 2) обновлять при кажому новом методе? 3) заглушка now 4) encoder? 5) без сеттеров, обновляются ли сами?
    // 6) почему поля не статик?

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(taskRepository, userRepository);
    }


    private final String username = "alexGibson";
    private final LocalDateTime createDate = LocalDateTime.now().minusDays(1);
    private final LocalDateTime deadline = LocalDateTime.now().plusDays(14);
    private final User user = User.builder()
            .id(1L)
            .fullname("Alex Gibson")
            .username("alexGibson")
            .email("alexgibson@mail.com")
            .password("password")
            .build();

    private final UserShortDto userShortDto = new UserShortDto("Alex Gibson", "alexGibson");

    private final TaskCreateDto taskCreateDto = new TaskCreateDto(
            "heading",
            "description",
            deadline,
            Status.WAITING,
            Priority.HIGH,
            Set.of("alexGibson")
    );

    private final TaskDto taskDto = TaskDto.builder()
            .id(1L)
            .heading("heading")
            .description("description")
            .createdDate(createDate)
            .deadline(deadline)
            .status(Status.WAITING)
            .priority(Priority.HIGH)
            .author(userShortDto)
            .executors(Set.of(userShortDto))
            .build();


    private final Task task = Task.builder()
            .id(1L)
            .heading("heading")
            .description("description")
            .createdDate(createDate)
            .deadline(deadline)
            .status(Status.WAITING)
            .priority(Priority.HIGH)
            .author(user)
            .executors(Set.of(user))
            .build();


    @Test
    void successCreateTest() {
        final Task taskBeforeSave = Task.builder()
                .heading("heading")
                .description("description")
                .createdDate(createDate)
                .deadline(deadline)
                .status(Status.WAITING)
                .priority(Priority.HIGH)
                .author(user)
                .executors(Set.of(user))
                .build();

        try (MockedStatic<LocalDateTime> timeMockedStatic = mockStatic(LocalDateTime.class, CALLS_REAL_METHODS)) {
            timeMockedStatic.when(LocalDateTime::now).thenReturn(createDate);
        }

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
//        when(taskRepository.save(taskBeforeSave))
//                .thenReturn((task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);


        final var result = taskService.create(taskCreateDto, username);

        assertThat(result.getId(), equalTo(taskDto.getId()));
        assertThat(result.getHeading(), equalTo(taskDto.getHeading()));
        assertThat(result.getDescription(), equalTo(taskDto.getDescription()));
        assertThat(result.getCreatedDate(), equalTo(taskDto.getCreatedDate()));
        assertThat(result.getDeadline(), equalTo(taskDto.getDeadline()));
        assertThat(result.getStatus(), equalTo(taskDto.getStatus()));
        assertThat(result.getPriority(), equalTo(taskDto.getPriority()));
        assertThat(result.getAuthor(), equalTo(taskDto.getAuthor()));
    }

    @Test
    void rangeTimeExceptionCreateTest() {
        final TaskCreateDto falseTask = new TaskCreateDto(
                "heading",
                "description",
                deadline.minusYears(1),
                Status.WAITING,
                Priority.HIGH,
                Set.of("alexGibson")
        );


        final var exception = assertThrows(
                RangeTimeException.class,
                () -> taskService.create(falseTask, username)
        );

        assertThat("Дедлайн не может быть раньше создания задачи", equalTo(exception.getMessage()));
    }
}

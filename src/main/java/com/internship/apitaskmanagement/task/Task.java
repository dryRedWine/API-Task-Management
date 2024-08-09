package com.internship.apitaskmanagement.task;

import com.internship.apitaskmanagement.comment.Comment;
import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import com.internship.apitaskmanagement.user.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String heading;

    @NotNull
    private String description;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    // Дедлайн является опциональной возможностью, можно его добавлять
    private LocalDateTime deadline;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "tasks_users",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @ToString.Exclude
    private Set<User> executors = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "task")
    private Set<Comment> comments = new HashSet<>();

    public Task() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(heading, task.heading) &&
                Objects.equals(description, task.description) &&
                Objects.equals(createdDate, task.createdDate) &&
                Objects.equals(deadline, task.deadline) &&
                status == task.status &&
                priority == task.priority &&
                Objects.equals(author, task.author) &&
                Objects.equals(executors, task.executors) &&
                Objects.equals(comments, task.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heading, description, createdDate, deadline, status, priority, author, executors, comments);
    }
}

package com.internship.apitaskmanagement.models;

import com.internship.apitaskmanagement.enums.Priority;
import com.internship.apitaskmanagement.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

//    @ManyToOne
//    @NotNull
//    @JoinColumn(name = "author_id")
//    private User author;
//
//    @ManyToOne
//    @NotNull
//    @JoinColumn(name = "executor_id")
//    private User executor;

//    private List<Subtask> subtasks;

    public Task() {
    }

    // equals and hashcode
}

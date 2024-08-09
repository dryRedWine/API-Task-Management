package com.internship.apitaskmanagement.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class UserShortDto {

    // DTO для вывода основной информации о пользователе, который является автором или исполнителем задачи

    @NotNull
    private String fullname;

    @NotNull
    private String username;
}

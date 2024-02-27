package com.internship.apitaskmanagement.user;

import com.internship.apitaskmanagement.user.models.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserShortDto toUserShortDto(User user) {
        return new UserShortDto(user.getFullname(), user.getUsername());
    }

    public static Set<UserShortDto> toUserShortDto(Set<User> users) {
        final Set<UserShortDto> result = new HashSet<>();
        for (User user : users)
            result.add(toUserShortDto(user));
        return result;
    }
}

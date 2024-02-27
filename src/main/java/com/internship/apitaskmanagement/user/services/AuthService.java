package com.internship.apitaskmanagement.user.services;

import com.internship.apitaskmanagement.security.dto.request.UserLoginDto;
import com.internship.apitaskmanagement.security.dto.request.UserRegistrationDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> authenticateUser(UserLoginDto userLoginDto);

    ResponseEntity<?> registerUser(UserRegistrationDto userRegistrationDto);
}

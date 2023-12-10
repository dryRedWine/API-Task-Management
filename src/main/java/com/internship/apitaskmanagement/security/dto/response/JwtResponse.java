package com.internship.apitaskmanagement.security.dto.response;

import java.util.Set;


public record JwtResponse(Long id, String username, String email, Set<String> roles, String accessToken) {}

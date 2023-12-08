package com.internship.apitaskmanagement.controllers;

import com.internship.apitaskmanagement.dto.request.UserLoginDto;
import com.internship.apitaskmanagement.dto.request.UserRegistrationDto;
import com.internship.apitaskmanagement.dto.response.JwtResponse;
import com.internship.apitaskmanagement.dto.response.MessageResponse;
import com.internship.apitaskmanagement.enums.ERole;
import com.internship.apitaskmanagement.models.Role;
import com.internship.apitaskmanagement.models.User;
import com.internship.apitaskmanagement.repositories.RoleRepository;
import com.internship.apitaskmanagement.repositories.UserRepository;
import com.internship.apitaskmanagement.security.jwt.JwtUtils;
import com.internship.apitaskmanagement.security.service.impl.MyUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserLoginDto userLoginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(new JwtResponse(userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByUsername(userRegistrationDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        //проверка для пароля?
        System.out.println("===================================");
        System.out.println(userRegistrationDto.getPassword());
        System.out.println("===================================");

        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = User.builder().username(userRegistrationDto.getUsername())
                .fullname(userRegistrationDto.getFullname())
                .email(userRegistrationDto.getEmail())
                .password(encoder.encode(userRegistrationDto.getPassword()))
                .build();

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

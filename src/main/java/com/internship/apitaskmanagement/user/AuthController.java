package com.internship.apitaskmanagement.user;

import com.internship.apitaskmanagement.security.dto.request.UserLoginDto;
import com.internship.apitaskmanagement.security.dto.request.UserRegistrationDto;
import com.internship.apitaskmanagement.user.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api-task-management/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserLoginDto userLoginDto) {
        log.info("User registration");
        return authService.authenticateUser(userLoginDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        log.info("User sign up");
        return authService.registerUser(userRegistrationDto);
    }

//    private final AuthenticationManager authenticationManager;
//
//    private final UserRepository userRepository;
//
//    private final RoleRepository roleRepository;
//
//    private final PasswordEncoder encoder;
//
//    private final JwtUtils jwtUtils;
//
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserLoginDto userLoginDto) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
//        Set<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toSet());
//
//        return ResponseEntity.ok(new JwtResponse(userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles,
//                jwt));
//    }
//
//// перенести реализацию в сервис
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
//        if (userRepository.existsByUsername(userRegistrationDto.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        User user = User.builder().username(userRegistrationDto.getUsername())
//                .fullname(userRegistrationDto.getFullname())
//                .email(userRegistrationDto.getEmail())
//                .password(encoder.encode(userRegistrationDto.getPassword()))
//                .build();
//
//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
}

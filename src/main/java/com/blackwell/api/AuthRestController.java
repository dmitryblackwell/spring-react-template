package com.blackwell.api;

import com.blackwell.config.jwt.JwtTokenProvider;
import com.blackwell.entity.Role;
import com.blackwell.entity.RoleName;
import com.blackwell.entity.User;
import com.blackwell.exception.AppException;
import com.blackwell.payload.UserSummary;
import com.blackwell.payload.request.LoginRequest;
import com.blackwell.payload.request.SignUpRequest;
import com.blackwell.payload.response.JwtAuthenticationResponse;
import com.blackwell.repository.RoleRepository;
import com.blackwell.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthRestController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return auth(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build();

        //TODO encrypt it on frontend
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        return auth(signUpRequest.getUsername(), signUpRequest.getPassword());
    }

    private ResponseEntity<?> auth(String username, String password) {

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty())
            throw new BadCredentialsException("Username/Password is incorrect.");

        User user = userOptional.get();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, new UserSummary(user)));
    }

}


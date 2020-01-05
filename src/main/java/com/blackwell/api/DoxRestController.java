package com.blackwell.api;

import com.blackwell.config.security.CurrentUser;
import com.blackwell.config.security.UserPrincipal;
import com.blackwell.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validate")
@AllArgsConstructor
@PreAuthorize("hasRole('USER')")
public class DoxRestController {

    private UserRepository userRepository;

    @GetMapping
    public UserPrincipal findAll(@CurrentUser UserPrincipal userPrincipal) {
        return userPrincipal;
    }

}
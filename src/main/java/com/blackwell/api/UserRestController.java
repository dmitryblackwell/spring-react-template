package com.blackwell.api;


import com.blackwell.entity.User;
import com.blackwell.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
@RequestMapping("/api/users/")
@AllArgsConstructor
@PreAuthorize("hasRole('USER')")
public class UserRestController {

    UserRepository userRepository;

    @GetMapping("/{username}")
    public User findUserByUsername(@PathVariable String username) throws Throwable {
        return userRepository.findByUsername(username)
                .orElseThrow((Supplier<Throwable>) () ->
                        new UsernameNotFoundException("User with this username not found"));
    }
}
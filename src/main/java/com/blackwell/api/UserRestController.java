package com.blackwell.api;


import com.blackwell.entity.User;
import com.blackwell.exception.UnauthorizedUserException;
import com.blackwell.payload.UserSummary;
import com.blackwell.repository.UserRepository;
import com.blackwell.config.security.CurrentUser;
import com.blackwell.config.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/api/users/")
@AllArgsConstructor
public class UserRestController {

    UserRepository userRepository;

    @GetMapping
    Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    User findUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username).get();
    }
}
package com.blackwell.config;

import com.blackwell.entity.Role;
import com.blackwell.entity.RoleName;
import com.blackwell.entity.User;
import com.blackwell.repository.RoleRepository;
import com.blackwell.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class StartDataInitialization implements ApplicationListener<ApplicationReadyEvent> {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Role roleUser = new Role();
        roleUser.setName(RoleName.ROLE_USER);

        Role roleAdmin = new Role();
        roleAdmin.setName(RoleName.ROLE_ADMIN);

        roleUser = roleRepository.save(roleUser);
        roleAdmin = roleRepository.save(roleAdmin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("user@gmail.com");
        user.setRoles(Set.of(roleUser));

        User userAdmin = new User();
        userAdmin.setUsername("admin");
        userAdmin.setPassword(passwordEncoder.encode("test"));
        userAdmin.setEmail("admin@gmail.com");
        userAdmin.setRoles(Set.of(roleAdmin));

        userRepository.save(user);
        userRepository.save(userAdmin);
    }
}

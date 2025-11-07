package com.example.demo.auth.config;

import com.example.demo.auth.entities.Role;
import com.example.demo.auth.entities.User;
import com.example.demo.auth.repository.RoleRepository;
import com.example.demo.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        final Role roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        final Optional<User> userAdminOptional = userRepository.findByUsername("admin");

        userAdminOptional.ifPresentOrElse(
                user -> System.out.println("admin ja existe"),
                () -> {
                    final User user = new User();

                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));

                    userRepository.save(user);
                }
        );
    }
}

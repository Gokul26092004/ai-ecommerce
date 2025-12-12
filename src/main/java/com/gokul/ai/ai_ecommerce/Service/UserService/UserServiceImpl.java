package com.gokul.ai.ai_ecommerce.Service.UserService;

import com.gokul.ai.ai_ecommerce.Dto.*;
import com.gokul.ai.ai_ecommerce.model.*;
import com.gokul.ai.ai_ecommerce.Repository.*;
import com.gokul.ai.ai_ecommerce.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return "User already exists";
        }

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(Set.of(role));

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(LoginDto dto) {
        var userOpt = userRepository.findByEmail(dto.getEmail());

        if (userOpt.isEmpty()) return "Invalid credentials";

        User user = userOpt.get();

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            return "Invalid credentials";
        }

        // Convert User into UserDetails
        org.springframework.security.core.userdetails.UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(
                                user.getRoles().stream()
                                        .map(Role::getName)
                                        .toArray(String[]::new)
                        )
                        .build();

        // 🔥 Pass BOTH userDetails AND userId
        return jwtUtil.generateToken(userDetails, user.getId());
    }


}


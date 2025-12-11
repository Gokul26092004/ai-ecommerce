package com.gokul.ai.ai_ecommerce.Controller;


import com.gokul.ai.ai_ecommerce.Dto.*;
import com.gokul.ai.ai_ecommerce.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) {
        System.out.println("Login API Hit");
        return userService.login(dto);
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello, secured world!";
    }
}

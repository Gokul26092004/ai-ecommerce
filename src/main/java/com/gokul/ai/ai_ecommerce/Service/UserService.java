package com.gokul.ai.ai_ecommerce.Service;


import com.gokul.ai.ai_ecommerce.Dto.*;

public interface UserService {
    String register(RegisterDto dto);
    String login(LoginDto dto);
}


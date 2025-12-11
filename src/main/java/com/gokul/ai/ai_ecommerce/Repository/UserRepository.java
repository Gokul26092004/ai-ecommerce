package com.gokul.ai.ai_ecommerce.Repository;


import com.gokul.ai.ai_ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}


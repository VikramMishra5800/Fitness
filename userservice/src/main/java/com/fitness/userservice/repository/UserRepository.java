package com.fitness.userservice.repository;

import com.fitness.userservice.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    Boolean existsByEmail(String email);

    Boolean existsByKeyCloakId(String userId);

    User findByEmail(String email);
}

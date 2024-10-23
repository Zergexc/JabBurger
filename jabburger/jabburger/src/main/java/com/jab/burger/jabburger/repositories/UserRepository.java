package com.jab.burger.jabburger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jab.burger.jabburger.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

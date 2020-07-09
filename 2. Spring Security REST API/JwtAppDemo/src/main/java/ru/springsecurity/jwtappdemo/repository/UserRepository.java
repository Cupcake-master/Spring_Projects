package ru.springsecurity.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springsecurity.jwtappdemo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByUsername(String username);
}

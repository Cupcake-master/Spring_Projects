package ru.springsecurity.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springsecurity.jwtappdemo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

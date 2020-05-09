package ru.itis.springdatajpaextended.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springdatajpaextended.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

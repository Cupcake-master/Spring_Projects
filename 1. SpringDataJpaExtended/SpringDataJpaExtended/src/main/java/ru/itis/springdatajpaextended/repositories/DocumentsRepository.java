package ru.itis.springdatajpaextended.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springdatajpaextended.models.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long> {
}

package ru.bulat.resume_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.bulat.resume_service.model.Resume;

import java.util.Optional;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, Long> {
    Optional<Resume> findById(Long id);
}

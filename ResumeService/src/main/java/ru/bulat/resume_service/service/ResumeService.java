package ru.bulat.resume_service.service;

import org.springframework.stereotype.Service;
import ru.bulat.resume_service.model.Resume;

import java.util.List;
import java.util.Optional;

/**
 * Service for performing basic operations on the resume entity
 * @author Bulat Bilalov
 * @version v1.0
 */

@Service
public interface ResumeService {

    /** Method for getting all entities from the database. */
    List<Resume> findAll();

    /** Method for getting an entity by id from the database. */
    Optional<Resume> findById(Long id);

    /** A method to remove a resume entity from the database. */
    void delete(Resume entity);

    /** A method for adding a resume entity from a database. */
    void add(Resume entity);
}

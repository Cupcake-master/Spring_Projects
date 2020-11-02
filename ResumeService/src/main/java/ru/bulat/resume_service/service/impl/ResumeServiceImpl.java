package ru.bulat.resume_service.service.impl;

import org.springframework.stereotype.Service;
import ru.bulat.resume_service.model.Resume;
import ru.bulat.resume_service.repository.ResumeRepository;
import ru.bulat.resume_service.service.ResumeService;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    @Override
    public Optional<Resume> findById(Long id) {
        return resumeRepository.findById(id);
    }

    @Override
    public void delete(Resume entity) {
        resumeRepository.delete(entity);
    }

    @Override
    public void add(Resume entity) {
        resumeRepository.save(entity);
    }
}

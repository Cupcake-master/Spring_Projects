package ru.bulat.resume_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bulat.resume_service.dto.ResumeDto;
import ru.bulat.resume_service.model.Resume;
import ru.bulat.resume_service.service.ResumeService;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for performing basic operations on the resume entity
 * @author Bulat Bilalov
 * @version v1.0
 */

@RestController
@RequestMapping("/rest")
public class ResumeRestController {

    private final ResumeService resumeService;

    public ResumeRestController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    /**
     * A method that searches for some essence of a resume using email.
     * If this entity is found, it will return it, and if not, it will return 204 error (NO_CONTENT).
     * @param id The field by which the search is going.
     */
    @GetMapping(value = "/resume/{id}")
    public ResponseEntity<Resume> getResumeByEmail(@PathVariable(name = "id") Long id) {
        Optional<Resume> resume = resumeService.findById(id);
        return resume.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * A method that returns all resume entities that are stored in the database.
     * If there are no entities in the database, it will return an error of 204 (NO_CONTENT),
     * and if there are, it will return all found entities in the database.
     */
    @GetMapping("/resumes")
    public ResponseEntity<List<Resume>> getResumes() {
        List<Resume> resumes = resumeService.findAll();
        return resumes.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    /**
     * Method for adding a resume entity to the database.
     * @param resumeDto The entity to add.
     */
    @PostMapping("/resume/add")
    public ResponseEntity<ResumeDto> addResume(@RequestBody ResumeDto resumeDto) {
        resumeService.add(resumeDto.toResume());
        return ResponseEntity.ok().build();
    }

    /**
     * Method for updating the entity of the resume to the database.
     * @param resume Updated entity.
     */
    @PostMapping("/resume/update")
    public ResponseEntity<Resume> updateResume(@RequestBody Resume resume){
        Optional<Resume> optionalResume = resumeService.findById(resume.getId());
        if (!optionalResume.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Resume result = optionalResume.get();
        resumeService.add(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Method for deleting a resume entity into a database.
     * @param resumeDto The entity to remove.
     */
    @PostMapping("/resume/delete")
    public ResponseEntity<ResumeDto> deleteResume(@RequestBody ResumeDto resumeDto) {
        resumeService.delete(resumeDto.toResume());
        return ResponseEntity.ok().build();
    }
}

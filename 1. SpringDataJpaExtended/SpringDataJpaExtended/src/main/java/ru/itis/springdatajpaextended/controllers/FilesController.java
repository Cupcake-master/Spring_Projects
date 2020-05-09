package ru.itis.springdatajpaextended.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springdatajpaextended.services.FilesService;

@RestController
public class FilesController {

    private final FilesService filesService;

    @Autowired
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping
    public ResponseEntity<?> init(){
        filesService.init();
        return ResponseEntity.ok().build();
    }
}

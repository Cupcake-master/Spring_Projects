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

    @GetMapping("/files/init")
    public ResponseEntity<?> init(){
        filesService.init();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/files/convert")
    public ResponseEntity<?> convert(){
        filesService.convert();
        return ResponseEntity.ok().build();
    }
}

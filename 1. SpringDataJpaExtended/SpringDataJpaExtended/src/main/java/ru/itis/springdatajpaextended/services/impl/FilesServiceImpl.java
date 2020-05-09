package ru.itis.springdatajpaextended.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springdatajpaextended.models.Document;
import ru.itis.springdatajpaextended.models.User;
import ru.itis.springdatajpaextended.repositories.UserRepository;
import ru.itis.springdatajpaextended.services.FilesService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesServiceImpl implements FilesService {
    private static final String FILES_PATH = "files";

    private final UserRepository userRepository;

    @Autowired
    public FilesServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void init() {
        User user = userRepository.getOne(1L);
        try(Stream<Path> filesPaths = Files.walk(Paths.get(FILES_PATH))) {
            filesPaths.filter(filePath -> filePath.toFile().isFile()).forEach(
                    filesPath -> {
                        File file = filesPath.toFile();
                        try {
                            Document document = Document.builder()
                                    .owner(user)
                                    .path(filesPath.toString())
                                    .size(file.length())
                                    .type(Files.probeContentType(filesPath))
                                    .build();
                        } catch (IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
            );
        }catch (IOException ex){
            throw new IllegalArgumentException(ex);
        }
    }
}

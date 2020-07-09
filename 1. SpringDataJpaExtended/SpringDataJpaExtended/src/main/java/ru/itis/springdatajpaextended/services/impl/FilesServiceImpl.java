package ru.itis.springdatajpaextended.services.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springdatajpaextended.models.Document;
import ru.itis.springdatajpaextended.models.User;
import ru.itis.springdatajpaextended.repositories.DocumentsRepository;
import ru.itis.springdatajpaextended.repositories.UserRepository;
import ru.itis.springdatajpaextended.services.FilesService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FilesServiceImpl implements FilesService {
    private static final String FILES_PATH = "files";
    private final static String CONVERTED_FILES_PATH = "converted_files";

    private final UserRepository userRepository;
    private final DocumentsRepository documentsRepository;

    @Autowired
    public FilesServiceImpl(UserRepository userRepository, DocumentsRepository documentsRepository) {
        this.userRepository = userRepository;
        this.documentsRepository = documentsRepository;
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
                            documentsRepository.save(document);
                        } catch (IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
            );
        }catch (IOException ex){
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public void convert() {
        List<Document> documents = documentsRepository.findAll();
        for (Document document: documents) {
            String newFileName = CONVERTED_FILES_PATH + "/" + document.getFileName() + ".jpg";
            if (document.getType().equals("application/pdf")){
                convertToPdf(document, newFileName);
            }
        }
    }

    private void convertToPdf(Document document, String newFileName) {
        try {
            PDDocument pdf = PDDocument.load(document.getSourceFile());
            PDFRenderer renderer = new PDFRenderer(pdf);
            BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
            ImageIOUtil.writeImage(image, newFileName, 300);
            pdf.close();
            File resultFile = new File(newFileName);
            document.setSourceFile(resultFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

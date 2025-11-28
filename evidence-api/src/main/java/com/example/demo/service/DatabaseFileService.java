package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class DatabaseFileService implements FileService {

    private static final String UPLOAD_DIR = "./uploads";

    @Override
    public String upload(String name, String type, byte[] conteudo) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(name);
        Files.write(filePath, conteudo);

        return filePath.toString();
    }
}
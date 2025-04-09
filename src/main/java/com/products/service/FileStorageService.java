package com.products.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(uploadDir, filename);
        Files.createDirectories(filepath.getParent());
        Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/" + filename;
    }
}

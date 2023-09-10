package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    void uploader(Long id, MultipartFile file) throws IOException;
}

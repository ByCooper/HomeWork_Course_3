package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploader(Long id, MultipartFile file) throws IOException;
    Avatar findAvatar(Long idStudent);
    List<Avatar> findAvatarPagination(Integer pageNum, Integer sizeVal);
}

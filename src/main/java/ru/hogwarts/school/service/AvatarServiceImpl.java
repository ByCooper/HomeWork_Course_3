package ru.hogwarts.school.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.AvatarRepository;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService{

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final Logger logger = LoggerFactory.logger(AvatarServiceImpl.class);

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;

    }

    @Override
    public void uploader(Long id, MultipartFile file) throws IOException {
        Student student = studentRepository.getById(id);
        logger.info("Was invoked method for upload avatar");

        Path filePath = Path.of(avatarsDir, student.getName() + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ){
            bis.transferTo(bos);
        };

        Avatar avatar = findAvatar(id);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(getImageData(filePath));

        avatarRepository.save(avatar);
    }

    private byte[] getImageData(Path filePath) throws IOException {
        logger.info("Was invoked method for getImageData avatar");
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage data = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = data.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(data, getExtension(filePath.getFileName().toString()), bos);
            return bos.toByteArray();

        }
    }

    public String getExtension(String fileName) {
        logger.info("Was invoked method for getExtension avatar");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar findAvatar(Long idStudent) {
        logger.info("Was invoked method for findAvatar avatar");
        return avatarRepository.findByStudent_Id(idStudent).orElse(new Avatar());
    }
    public List<Avatar> findAvatarPagination(Integer pageNum, Integer sizeVal) {
        logger.info("Was invoked method for findAvatarPagination avatar");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, sizeVal);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}

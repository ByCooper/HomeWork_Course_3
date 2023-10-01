package ru.hogwarts.school.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.FacultyRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final Logger logger = LoggerFactory.logger(FacultyServiceImpl.class);
    private final FacultyRepository facultyRepository;
    private final StudentServiceImpl studentService;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentServiceImpl studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Was invoked method for add faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        if (id < 0 || find(id).equals(null) || !faculty.getClass().equals(Faculty.class)) {
            logger.error("Error! Неправильно введенное значение");
        }
        Faculty faculty1 = find(id);
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        return facultyRepository.save(faculty1);
    }

    @Override
    public Faculty find(Long id) {
        logger.info("Was invoked method for find faculty");
        if (id < 0 || facultyRepository.findById(id).isEmpty()) {
            logger.error("Error! There is not faculty with id =" + id);
        }
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        logger.info("Was invoked method for remove faculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> filter(String color) {
        logger.info("Was invoked method for filter faculty");
        return facultyRepository.findAll().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("Was invoked method for getAll faculty");
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findByNameContainsIgnoreCase(String name) {
        logger.info("Was invoked method for findByNameContainsIgnoreCase faculty");
        return facultyRepository.findByNameContainsIgnoreCase(name);
    }
    @Override
    public Collection<Faculty> findByColorContainsIgnoreCase(String color) {
        logger.info("Was invoked method for findByColorContainsIgnoreCase faculty");
        return facultyRepository.findByColorContainsIgnoreCase(color);
    }
    @Override
    public List<Student> getStudentFromFaculty(Long id) {
        logger.info("Was invoked method for getStudentFromFaculty faculty");
        return studentService.findByFacultyId(id);
    }
}

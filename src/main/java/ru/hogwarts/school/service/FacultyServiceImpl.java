package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.FacultyRepository;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;
    private final StudentServiceImpl studentService;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentServiceImpl studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    @Override
    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty faculty1 = find(id);
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        return facultyRepository.save(faculty1);
    }

    @Override
    public Faculty find(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> filter(String color) {
        return facultyRepository.findAll().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByNameContainsIgnoreCase(String name) {
        return facultyRepository.findByNameContainsIgnoreCase(name);
    }
    public Collection<Faculty> findByColorContainsIgnoreCase(String color) {
        return facultyRepository.findByColorContainsIgnoreCase(color);
    }

    public List<Student> getStudentFromFaculty(Long id) {
        return studentService.findByFacultyId(id);
    }
}

package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty update(Long id, Faculty faculty);
    Faculty find(Long id);
    void remove(Long id);
    Collection<Faculty> filter(String color);
    Collection<Faculty> getAll();
    Collection<Faculty> findByNameContainsIgnoreCase(String name);
    Collection<Faculty> findByColorContainsIgnoreCase(String color);
    List<Student> getStudentFromFaculty(Long id);
    String getLongNameFaculty();
    Integer getStreamIterate();
}

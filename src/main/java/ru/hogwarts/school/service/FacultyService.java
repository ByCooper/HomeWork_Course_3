package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty update(Faculty faculty);
    Faculty find(Long id);
    void remove(Long id);
    Collection<Faculty> filter(String color);
    Collection<Faculty> getAll();
}

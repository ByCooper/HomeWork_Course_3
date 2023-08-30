package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student add(Student student);
    Student update(Student student);
    Student find(Long id);
    Student  remove(Long id);
    Collection<Student> filter(int age);
}

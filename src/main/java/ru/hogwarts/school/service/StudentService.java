package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentByCategory;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Student add(Student student);
    Student update(Student student);
    Student find(Long id);
    void   remove(Long id);
    Collection<Student> filter(int age);
    Collection<Student> getAll();
    Collection<Student> findByAgeBetween(int min, int max);
    Faculty getFaculty(Long id);
    List<Student> findByFacultyId(Long id);
    Integer getStudentByCategory();
    Integer getStudentByAvgAge();
    List<StudentByCategory> getStudentByOffset();
}

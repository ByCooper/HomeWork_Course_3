package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentByCategory;
import ru.hogwarts.school.model.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student find(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> filter(int age) {
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }
    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
    @Override
    public Faculty getFaculty(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }
    @Override
    public List<Student> findByFacultyId(Long id) {
        return studentRepository.findByFaculty_Id(id);
    }
    @Override
    public Integer getStudentByCategory() {
        return studentRepository.getStudentByCategory();
    }
    @Override
    public Integer getStudentByAvgAge() {
        return studentRepository.getStudentByAvgAge();
    }
    @Override
    public List<StudentByCategory> getStudentByOffset() {
        return studentRepository.getStudentByOffset();
    }
}

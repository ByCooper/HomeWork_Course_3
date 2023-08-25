package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentServiceImpl implements StudentService{
    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long generateId = 0L;

    @Override
    public Student add(Student student) {
        student.setId(++generateId);
        studentMap.put(generateId, student);
        return student;
    }

    @Override
    public Student update(Student student) {
        studentMap.get(student.getId()).setAge(student.getAge());
        studentMap.get(student.getId()).setName(student.getName());
        return studentMap.get(student.getId());
    }

    @Override
    public Student find(Long id) {
        return studentMap.get(id);
    }

    @Override
    public Student remove(Long id) {
        studentMap.remove(id);
        return studentMap.get(id);
    }

    @Override
    public Collection<Student> filter(int age) {
        return studentMap.values().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }
}

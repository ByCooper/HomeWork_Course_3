package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;


@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student add = studentService.add(student);
        return ResponseEntity.ok(add);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> read(@PathVariable Long id) {
        Student read = studentService.find(id);
        return ResponseEntity.ok(read);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student update = studentService.update(student);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        Student delete = studentService.remove(id);
        return ResponseEntity.ok(delete);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> filterAge(@PathVariable int age) {
        Collection<Student> filter = studentService.filter(age);
        return ResponseEntity.ok(filter);
    }
}

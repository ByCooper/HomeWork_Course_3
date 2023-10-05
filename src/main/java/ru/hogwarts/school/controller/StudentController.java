package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;


@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
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
        if (read == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(read);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student update = studentService.update(student);
        if (update == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age-find")
    public ResponseEntity<Collection<Student>> filterAge(@RequestParam("age") int age) {
        Collection<Student> filter = studentService.filter(age);
        return ResponseEntity.ok(filter);
    }

    @GetMapping("/age-between")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam("minAge") int minAge,
                                                                @RequestParam("maxAge") int maxAge) {
        Collection<Student> filter = studentService.findByAgeBetween(minAge, maxAge);
        return ResponseEntity.ok(filter);
    }
    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAll() {
        Collection<Student> filter = studentService.getAll();
        return ResponseEntity.ok(filter);
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = studentService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/sorted")
    public ResponseEntity<Collection<String>> getFindFirstLiterStudent(@RequestParam("liter") String liter) {
        Collection<String> list = studentService.getFirstLiterStudent(liter);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/age-avg-stream")
    public ResponseEntity<Integer> getAgeAverageStudent() {
        Integer avgAge = studentService.getAgeAverageStudent();
        return ResponseEntity.ok(avgAge);
    }
}

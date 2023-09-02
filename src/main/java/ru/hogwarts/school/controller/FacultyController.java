package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;


@RequestMapping("faculty")
@RestController
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty) {
        Faculty add = facultyService.add(faculty);
        return ResponseEntity.ok(add);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> read(@PathVariable Long id) {
        Faculty read = facultyService.find(id);
        if (read == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(read);
    }

    @PutMapping
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty update = facultyService.update(faculty);
        if (update == null) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        facultyService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Collection<Faculty>> filterFacult(@PathVariable String color) {
        Collection<Faculty> filter = facultyService.filter(color);
        return ResponseEntity.ok(filter);
    }

    @GetMapping("/color/all")
    public ResponseEntity<Collection<Faculty>> allFaculty() {
        Collection<Faculty> filter = facultyService.getAll();
        return ResponseEntity.ok(filter);
    }
}

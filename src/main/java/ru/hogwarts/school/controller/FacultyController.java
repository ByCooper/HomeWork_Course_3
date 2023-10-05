package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;


@RequestMapping("faculty")
@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
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

    @PutMapping("{id}")
    public ResponseEntity<Faculty> update(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty update = facultyService.update(id, faculty);
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

    @GetMapping("/color-find")
    public ResponseEntity<Collection<Faculty>> filterFaculty(@RequestParam("color") String color) {
        Collection<Faculty> filter = facultyService.filter(color);
        return ResponseEntity.ok(filter);
    }
    @GetMapping(path = "/find")
    public ResponseEntity<Collection<Faculty>> findFaculty(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByNameContainsIgnoreCase(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColorContainsIgnoreCase(color));
        }
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> allFaculty() {
        Collection<Faculty> filter = facultyService.getAll();
        return ResponseEntity.ok(filter);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<Student>> getStudentsFromFaculty(@PathVariable Long id) {
        List<Student> students = facultyService.getStudentFromFaculty(id);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/long-name-faculty")
    public ResponseEntity<String> getLongNameFaculty() {
        String longName = facultyService.getLongNameFaculty();
        return ResponseEntity.ok(longName);
    }

    @GetMapping("/step-four")
    public ResponseEntity<Integer> getStreamIterate() {
        Integer item = facultyService.getStreamIterate();
        return ResponseEntity.ok(item);
    }
}

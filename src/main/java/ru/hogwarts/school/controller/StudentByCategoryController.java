package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.StudentByCategory;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/student-by-category")
public class StudentByCategoryController {

    private final StudentServiceImpl studentService;

    public StudentByCategoryController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/count")
    public Integer getStudentByCategory() {
       return studentService.getStudentByCategory();
    }
    @GetMapping(path = "/avg-age")
    public Integer getStudentByAvgAge() {
       return studentService.getStudentByAvgAge();
    }
    @GetMapping(path = "/offset")
    public List<StudentByCategory> getStudentByOffset() {
       return studentService.getStudentByOffset();
    }
}

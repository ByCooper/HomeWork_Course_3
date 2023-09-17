package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.FacultyRepository;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    FacultyRepository facultyRepository;

    @SpyBean
    StudentServiceImpl studentService;

    @SpyBean
    StudentController studentController;
    @SpyBean
    FacultyServiceImpl facultyService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception{

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");

        Student student = new Student();
        student.setId(1L);
        student.setName("Igor Moroz");
        student.setAge(27);
        student.setFaculty(faculty);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(post("/student")
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    void testRead() throws Exception{
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");

        Student student = new Student();
        student.setId(1L);
        student.setName("Igor Moroz");
        student.setAge(27);
        student.setFaculty(faculty);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(get("/student/" + student.getId())
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    void testUpdate() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");

        Student student = new Student();
        student.setId(1L);
        student.setName("Igor Moroz");
        student.setAge(27);
        student.setFaculty(faculty);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(put("/student")
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    void testGetAll() throws Exception{
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");

        Student student = new Student();
        student.setId(1L);
        student.setName("Igor Moroz");
        student.setAge(27);
        student.setFaculty(faculty);

        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Igor Moroz");
        student1.setAge(27);
        student1.setFaculty(faculty);

        Student student2 = new Student();
        student2.setId(1L);
        student2.setName("Igor Moroz");
        student2.setAge(27);
        student2.setFaculty(faculty);

        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        list.add(student2);

        when(studentRepository.findAll()).thenReturn(list);

        mockMvc.perform(get("/student/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
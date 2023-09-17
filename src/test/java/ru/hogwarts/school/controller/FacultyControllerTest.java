package ru.hogwarts.school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.FacultyRepository;
import ru.hogwarts.school.model.StudentRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    FacultyRepository facultyRepository;

    @MockBean
    StudentRepository studentRepository;

    @SpyBean
    StudentServiceImpl studentService;

    @SpyBean
    FacultyController facultyController;
    @SpyBean
    FacultyServiceImpl facultyService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testAdd() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(post("/faculty")
                .content(objectMapper.writeValueAsString(faculty))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

    }

    @Test
    void testRead() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(get("/faculty/" + faculty.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }

    @Test
    void testUpdate() throws Exception{

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));


        mockMvc.perform(put("/faculty/" + faculty.getId())
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }


    @Test
    void testAllFaculty() throws Exception{

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Griffindor");
        faculty.setColor("green");
        Faculty faculty1 = new Faculty();
        faculty1.setId(2L);
        faculty1.setName("Griffindor");
        faculty1.setColor("blue");
        Faculty faculty2 = new Faculty();
        faculty2.setId(3L);
        faculty2.setName("Griffindor");
        faculty2.setColor("grey");

        List<Faculty> list = new ArrayList<>();
        list.add(faculty);
        list.add(faculty1);
        list.add(faculty2);

        when(facultyRepository.findAll()).thenReturn(list);


        mockMvc.perform(get("/faculty/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
package ru.hogwarts.school.controller;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.SchoolApplication;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest(classes = SchoolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @Autowired
    TestRestTemplate template;

    @Test
    void testCreate() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());

        ResponseEntity<Student> studentBody = template.postForEntity("/student", student, Student.class);
        assertThat(studentBody).isNotNull();
        assertThat(studentBody.getStatusCode()).isEqualTo(HttpStatus.OK);
        Student body = studentBody.getBody();
        assertThat(body.getId()).isEqualTo(1L);
        assertThat(body.getName()).isEqualTo(student.getName());
        assertThat(body.getAge()).isEqualTo(student.getAge());
        assertThat(body.getFaculty().getName()).isEqualTo(student.getFaculty().getName());
    }

    @Test
    void read() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studentBody = template.postForEntity("/student", student, Student.class);

        studentBody = template.getForEntity("/student/" + studentBody.getBody().getId(), Student.class);
        assertThat(studentBody).isNotNull();
        assertThat(studentBody.getStatusCode()).isEqualTo(HttpStatus.OK);
        Student body = studentBody.getBody();
        assertThat(body.getId()).isEqualTo(1L);
        assertThat(body.getName()).isEqualTo(student.getName());
        assertThat(body.getAge()).isEqualTo(student.getAge());
        assertThat(body.getFaculty().getName()).isEqualTo(student.getFaculty().getName());
    }

    @Test
    void update() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studentBody = template.postForEntity("/student", student, Student.class);

        student.setName("John Wick");
        student.setId(1L);
        template.put("/student", student);
        studentBody = template.getForEntity("/student/" + studentBody.getBody().getId(), Student.class);

        assertThat(studentBody).isNotNull();
        assertThat(studentBody.getStatusCode()).isEqualTo(HttpStatus.OK);
        Student body = studentBody.getBody();
        assertThat(body.getId()).isEqualTo(1L);
        assertThat(body.getName()).isEqualTo(student.getName());
        assertThat(body.getAge()).isEqualTo(student.getAge());
        assertThat(body.getFaculty().getName()).isEqualTo(student.getFaculty().getName());
    }

    @Test
    void delete() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studentBody = template.postForEntity("/student", student, Student.class);

        template.delete("/student/" + studentBody.getBody().getId());
        studentBody = template.getForEntity("/student/" + studentBody.getBody().getId(), Student.class);
        assertThat(studentBody).isNotNull();
        assertThat(studentBody.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void filterAge() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());
        template.postForEntity("/student", student, Student.class);

        Student student1 = new Student();
        student1.setName("Bred Pitt");
        student1.setAge(15);
        student1.setFaculty(facultyBody.getBody());
        template.postForEntity("/student", student1, Student.class);

        Student student2 = new Student();
        student2.setName("Pedro Pascal");
        student2.setAge(25);
        student2.setFaculty(facultyBody.getBody());
        template.postForEntity("/student", student2, Student.class);

        ResponseEntity<Student> studentBody = template.getForEntity("/student/" + 1L, Student.class);
        List<Student> expected = new ArrayList<>();
        expected.add(studentBody.getBody());
        ResponseEntity<Collection> response = template.getForEntity("/student/age-find?age=" + 24, Collection.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().toString()).isEqualTo(Arrays.toString(expected.toArray()));
    }

    @Test
    void findByAgeBetween() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studResponse = template.postForEntity("/student", student, Student.class);

        Student student1 = new Student();
        student1.setName("Bred Pitt");
        student1.setAge(15);
        student1.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studResponse1 = template.postForEntity("/student", student1, Student.class);

        Student student2 = new Student();
        student2.setName("Pedro Pascal");
        student2.setAge(25);
        student2.setFaculty(facultyBody.getBody());
        template.postForEntity("/student", student2, Student.class);

        List<Student> expected = new ArrayList<>();
        expected.add(studResponse.getBody());
        expected.add(studResponse1.getBody());

        ResponseEntity<Collection> response = template.getForEntity("/student/age-between?minAge=" + 10 + "&maxAge=" + 24, Collection.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().toString()).isEqualTo(Arrays.toString(expected.toArray()));
    }

    @Test
    void getAll() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studResponse = template.postForEntity("/student", student, Student.class);

        Student student1 = new Student();
        student1.setName("Bred Pitt");
        student1.setAge(15);
        student1.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studResponse1 = template.postForEntity("/student", student1, Student.class);

        Student student2 = new Student();
        student2.setName("Pedro Pascal");
        student2.setAge(25);
        student2.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studResponse2 = template.postForEntity("/student", student2, Student.class);

        List<Student> expected = new ArrayList<>();
        expected.add(studResponse.getBody());
        expected.add(studResponse1.getBody());
        expected.add(studResponse2.getBody());

        ResponseEntity<Collection> response = template.getForEntity("/student/all", Collection.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().toString()).isEqualTo(Arrays.toString(expected.toArray()));
    }

    @Test
    void getFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");
        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        facultyBody = template.getForEntity("/faculty/" + facultyBody.getBody().getId(), Faculty.class);

        Student student = new Student();
        student.setName("John Travolta");
        student.setAge(24);
        student.setFaculty(facultyBody.getBody());
        ResponseEntity<Student> studResponse = template.postForEntity("/student", student, Student.class);

        ResponseEntity<Faculty> response = template.getForEntity("/student/faculty/" + studResponse.getBody().getId(), Faculty.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(studResponse.getBody().getFaculty());
    }
}
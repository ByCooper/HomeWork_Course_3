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
class FacultyControllerTest {

    @Autowired
    TestRestTemplate template;

    @Test
    void testCreate() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");

        ResponseEntity<Faculty> response = template.postForEntity("/faculty", faculty, Faculty.class);
//create
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
//        assertThat(body.getId()).isEqualTo(1L);
        assertThat(body.getName()).isEqualTo(faculty.getName());
        assertThat(body.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void testRead() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");

        ResponseEntity<Faculty> response = template.postForEntity("/faculty", faculty, Faculty.class);
//read
        response = template.getForEntity("/faculty/" + response.getBody().getId(), Faculty.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
        assertThat(body.getId()).isEqualTo(1L);
        assertThat(body.getName()).isEqualTo(faculty.getName());
        assertThat(body.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void testUpdate() {
        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");

        ResponseEntity<Faculty> response = template.postForEntity("/faculty", faculty, Faculty.class);

        //update
        faculty.setName("SKY-PRO");
        faculty.setColor("black-and-yellow");
        faculty.setId(response.getBody().getId());
        template.put("/faculty/" + response.getBody().getId(), faculty);

        response = template.getForEntity("/faculty/" + response.getBody().getId(), Faculty.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
//        assertThat(body.getId()).isEqualTo(1L);
        assertThat(body.getName()).isEqualTo(faculty.getName());
        assertThat(body.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void testDelete() {

        Faculty faculty = new Faculty();
        faculty.setName("SKY-PRO");
        faculty.setColor("green-blue");

        ResponseEntity<Faculty> response = template.postForEntity("/faculty", faculty, Faculty.class);
        //delete
        template.delete("/faculty/" + response.getBody().getId());
        response = template.getForEntity("/faculty/" + response.getBody().getId(), Faculty.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testFilter() {
        Faculty faculty = new Faculty();
        faculty.setName("JEDI-school");
        faculty.setColor("green-blue");
        Faculty faculty2 = new Faculty();
        faculty2.setName("SKY-PRO");
        faculty2.setColor("green-blue");
        Faculty faculty1 = new Faculty();
        faculty1.setName("SKY-ENG");
        faculty1.setColor("pink-white");

        template.postForEntity("/faculty", faculty, Faculty.class);
        template.postForEntity("/faculty", faculty1, Faculty.class);
        template.postForEntity("/faculty", faculty2, Faculty.class);

        List<Faculty> list = new ArrayList<>();
        list.add(new Faculty(1l, "JEDI-school", "green-blue"));
        list.add(new Faculty(3l, "SKY-PRO", "green-blue"));

        ResponseEntity<Collection> response = template.getForEntity("/faculty/color/" + faculty.getColor(), Collection.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(list.size());
        assertThat(response.getBody().toString()).isEqualTo(Arrays.toString(list.toArray()));

    }

    @Test
    void testGetAll() {
        Faculty faculty = new Faculty();
        faculty.setName("JEDI-school");
        faculty.setColor("green-blue");
        Faculty faculty2 = new Faculty();
        faculty2.setName("SKY-PRO");
        faculty2.setColor("green-blue");
        Faculty faculty1 = new Faculty();
        faculty1.setName("SKY-ENG");
        faculty1.setColor("pink-white");

        ResponseEntity<Faculty> facultyBody = template.postForEntity("/faculty", faculty, Faculty.class);
        ResponseEntity<Faculty> facultyBody1 = template.postForEntity("/faculty", faculty1, Faculty.class);
        ResponseEntity<Faculty> facultyBody2 = template.postForEntity("/faculty", faculty2, Faculty.class);

        List<Faculty> list = new ArrayList<>();
        list.add(facultyBody.getBody());
        list.add(facultyBody1.getBody());
        list.add(facultyBody2.getBody());

        ResponseEntity<Collection> response = template.getForEntity("/faculty/all", Collection.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(list.size());
        assertThat(response.getBody().toString()).isEqualTo(Arrays.toString(list.toArray()));
    }

    @Test
    void testFindFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("JEDI-school");
        faculty.setColor("green-blue");
        Faculty faculty2 = new Faculty();
        faculty2.setName("SKY-PRO");
        faculty2.setColor("green-blue");
        Faculty faculty1 = new Faculty();
        faculty1.setName("SKY-ENG");
        faculty1.setColor("pink-white");

        template.postForEntity("/faculty", faculty, Faculty.class);
        template.postForEntity("/faculty", faculty1, Faculty.class);
        template.postForEntity("/faculty", faculty2, Faculty.class);

        List<Faculty> list = new ArrayList<>();
        list.add(new Faculty(1l, "JEDI-school", "green-blue"));

        List<Faculty> list1 = new ArrayList<>();
        list1.add(new Faculty(1l, "JEDI-school", "green-blue"));
        list1.add(new Faculty(3l, "SKY-PRO", "green-blue"));

        ResponseEntity<Collection> response = template.getForEntity("/faculty?name=" + faculty.getName(), Collection.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().toString()).isEqualTo(Arrays.toString(list.toArray()));

        ResponseEntity<Collection> response1 = template.getForEntity("/faculty?color=" + "green-blue", Collection.class);
        assertThat(response1).isNotNull();
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody().toString()).isEqualTo(Arrays.toString(list1.toArray()));
    }

    @Test
    void testGetStudentFromFaculty() {
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

        ResponseEntity<Faculty> response = template.getForEntity("/faculty/student/" + 1L, Faculty.class);
        assertThat(response).isNotNull();
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
package ru.hogwarts.school.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentByCategory;
import ru.hogwarts.school.model.StudentRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@Service
public class StudentServiceImpl implements StudentService{

    private final Logger logger = LoggerFactory.logger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        logger.info("Was invoked method for add student");
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        logger.info("Was invoked method for update student");
        if (student.equals(null) || !student.getClass().equals(Student.class)) {
            logger.error("There is not student with student = " + student);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student find(Long id) {
        logger.info("Was invoked method for find student");
        if (studentRepository.findById(id).isEmpty()) {
            logger.error("There is not student with id = " + id);
        }
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        logger.info("Was invoked method for remove student");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> filter(int age) {
        logger.info("Was invoked method for filter student");
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("Was invoked method for getAll student");
        return studentRepository.findAll();
    }
    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for findByAgeBetween student");
        return studentRepository.findByAgeBetween(min, max);
    }
    @Override
    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method for getFaculty student");
        if (id < 0 || studentRepository.findById(id).isEmpty()) {
            logger.error("There is not student with id = " + id);
        }
        return studentRepository.findById(id).get().getFaculty();
    }
    @Override
    public List<Student> findByFacultyId(Long id) {
        logger.info("Was invoked method for findByFacultyId student");
        return studentRepository.findByFaculty_Id(id);
    }
    @Override
    public Integer getStudentByCategory() {
        logger.info("Was invoked method for getStudentByCategory student");
        return studentRepository.getStudentByCategory();
    }
    @Override
    public Integer getStudentByAvgAge() {
        logger.info("Was invoked method for getStudentByAvgAge student");
        return studentRepository.getStudentByAvgAge();
    }
    @Override
    public List<StudentByCategory> getStudentByOffset() {
        logger.info("Was invoked method for getStudentByOffset student");
        return studentRepository.getStudentByOffset();
    }

    @Override
    public List<String> getFirstLiterStudent(String liter) {
        logger.info("Was invoked method for getFirstLiterStudent student");
        return studentRepository.findAll().stream()
                .map(e -> e.getName())
                .map(e -> e.substring(0,1).toUpperCase() + e.substring(1))
                .filter(e -> e.startsWith(liter))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getAgeAverageStudent() {
        logger.info("Was invoked method for getAgeAverageStudent student");
        List<Integer> list = studentRepository.findAll().stream()
                .map(e -> e.getAge())
                .collect(Collectors.toList());
        OptionalDouble average = list.stream().mapToInt(e -> e).average();
        return (int) average.getAsDouble();
    }
    // Streams
    @Override
    public List<String> getStreamStudent() {
        logger.info("Was invoked method for getStreamStudent student");
        System.out.println(getName().get(0));
        System.out.println(getName().get(1));
        new Thread(() ->
        {
            System.out.println(getName().get(2));
            System.out.println(getName().get(3));
            System.out.println(getName().get(4));
        }).start();
        new Thread(() -> {
            System.out.println(getName().get(5));
            System.out.println(getName().get(6));
            System.out.println(getName().get(7));
        }).start();
        System.out.println(getName().get(8));
        System.out.println(getName().get(9));

        return getName();
    }

    @Override
    public List<String> getSyncName() {
        logger.info("Was invoked method for getSyncName student");
        doPrint(getName().get(0));
        doPrint(getName().get(1));
        doPrint(getName().get(2));
        doPrint(getName().get(3));
        new Thread(() -> {
            doPrint(getName().get(4));
            doPrint(getName().get(5));
            doPrint(getName().get(6));
        }).start();
        new Thread(() -> {
            doPrint(getName().get(7));
            doPrint(getName().get(8));
            doPrint(getName().get(9));
        }).start();

        return getName();
    }

    private void doPrint(Object object) {
        synchronized (flag) {
            System.out.println(object);
        }
    }

    private List<String> getName() {
        logger.info("Was invoked method for getName student");
        return studentRepository.findAll().stream()
                .map(e -> e.getName())
                .map(e -> e.substring(0,1).toUpperCase() + e.substring(1))
                .collect(Collectors.toList());
    }

    final Object flag = new Object();
}

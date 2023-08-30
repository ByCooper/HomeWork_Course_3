package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    StudentServiceImpl cut = new StudentServiceImpl();


    @Test
    void add() {
        //Подготовка входных данных
        Student actual = cut.add(new Student(0L, "Harry", 35));

        //Подготовка ожидаемого результата
        Student expected = new Student(1L, "Harry", 35);

        //Запуск теста
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        //Подготовка входных данных
        Student student = new Student(0L, "Harry", 35);
        cut.add(student);

        //Подготовка ожидаемого результата
        Student expected = new Student(1L, "Harry", 40);

        //Запуск теста
        Student actual = cut.update(new Student(1L, "Harry", 40));
        assertEquals(expected, actual);
    }

    @Test
    void find() {
        //Подготовка входных данных
        Student student = cut.add(new Student(0L, "Harry", 35));

        //Подготовка ожидаемого результата
        Student expected = new Student(1L, "Harry", 35);

        //Запуск теста
        Student actual = cut.find(1L);
        assertEquals(expected, actual);
    }

    @Test
    void remove() {
        //Подготовка входных данных
        Student student = cut.add(new Student(0L, "Harry", 35));

        //Подготовка ожидаемого результата
        Student expected = null;

        //Запуск теста
        Student actual = cut.remove(student.getId());
        assertEquals(expected, actual);
    }

    @Test
    void filter() {
        //Подготовка входных данных
        Student student = cut.add(new Student(0L, "Harry", 35));
        Student student1 = cut.add(new Student(0L, "Rhon", 91));
        Student student2 = cut.add(new Student(0L, "Thor", 35));
        Student student3 = cut.add(new Student(0L, "Yoda", 23));

        //Подготовка ожидаемого результата
        Collection<Student> expected = new ArrayList<>(List.of(new Student(1L, "Harry", 35), new Student(3L, "Thor", 35)));

        //Запуск теста
        Collection<Student> actual = cut.filter(35);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
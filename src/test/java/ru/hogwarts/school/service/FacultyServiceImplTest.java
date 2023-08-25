package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceImplTest {

    FacultyServiceImpl cut = new FacultyServiceImpl();

    @Test
    void add() {
        //Подготовка входных данных
        Faculty faculty = new Faculty(0L, "Griffindor", "red-yellow");

        //Подготовка ожидаемого результата
        Faculty expected = new Faculty(1L, "Griffindor", "red-yellow");

        //Запуск теста
        Faculty actual = cut.add(faculty);
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        //Подготовка входных данных
        Faculty faculty = new Faculty(0L, "Griffindor", "red-yellow");
        cut.add(faculty);

        //Подготовка ожидаемого результата
        Faculty expected = new Faculty(1L, "Griffindor", "red");

        //Запуск теста
        Faculty actual = cut.update(new Faculty(1L, "Griffindor", "red"));
        assertEquals(expected, actual);
    }

    @Test
    void find() {
        //Подготовка входных данных
        Faculty faculty = new Faculty(0L, "Griffindor", "red-yellow");
        cut.add(faculty);

        //Подготовка ожидаемого результата
        Faculty expected = new Faculty(1L, "Griffindor", "red-yellow");

        //Запуск теста
        Faculty actual = cut.find(faculty.getId());
        assertEquals(expected, actual);
    }

    @Test
    void remove() {
        //Подготовка входных данных
        Faculty faculty = new Faculty(0L, "Griffindor", "red-yellow");
        cut.add(faculty);

        //Подготовка ожидаемого результата
        Faculty expected = null;

        //Запуск теста
        Faculty actual = cut.remove(faculty.getId());
        assertEquals(expected, actual);
    }

    @Test
    void filter() {
        //Подготовка входных данных
        cut.add(new Faculty(0L, "Griffindor", "red-yellow"));
        cut.add(new Faculty(0L, "Slizzerin", "green-white"));
        cut.add(new Faculty(0L, "Jedi-shcool", "green-white"));
        cut.add(new Faculty(0L, "Wall-Stret", "grey"));

        //Подготовка ожидаемого результата
        Collection<Faculty> expected = new ArrayList<>(List.of(new Faculty(2L, "Slizzerin", "green-white"), new Faculty(3L, "Jedi-shcool", "green-white")));

        //Запуск теста
        Collection<Faculty> actual = cut.filter("green-white");
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
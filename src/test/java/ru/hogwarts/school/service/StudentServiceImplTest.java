package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    StudentServiceImpl cut;

    @Mock
    StudentRepository cutMock;


    @Test
    void testAdd() {
        //Подготовка входных данных
        when(cutMock.save(any())).thenReturn(new Student(1L, "Harry", 35));
        Student actual = cut.add(new Student(0L, "Harry", 35));

        //Подготовка ожидаемого результата
        Student expected = new Student(1L, "Harry", 35);

        //Запуск теста
        assertEquals(expected, actual);
        verify(cutMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        //Подготовка входных данных
        when(cutMock.save(any())).thenReturn(new Student(1L, "Harry", 40));

        //Подготовка ожидаемого результата
        Student expected = new Student(1L, "Harry", 40);

        //Запуск теста
        Student actual = cut.update(new Student(1L, "Harry", 40));
        assertEquals(expected, actual);
        verify(cutMock, times(1)).save(any());
    }

    @Test
    void testFind() {
        //Подготовка входных данных
        when(cutMock.findById(any())).thenReturn(Optional.of(new Student(1L, "Harry", 35)));

        //Подготовка ожидаемого результата
        Student expected = new Student(1L, "Harry", 35);

        //Запуск теста
        Student actual = cut.find(1L);
        assertEquals(expected, actual);
        verify(cutMock, times(1)).findById(any());
    }

    @Test
    void testRemove() {
        //Запуск теста
        cut.remove(1L);
        verify(cutMock, times(1)).deleteById(any());
    }

    @Test
    void testFilter() {
        //Подготовка входных данных
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "Harry", 35));
        students.add(new Student(2L, "Rhon", 91));
        students.add(new Student(3L, "Thor", 35));
        students.add(new Student(4L, "Yoda", 23));
        when(cutMock.findAll()).thenReturn(students);

        //Подготовка ожидаемого результата
        List<Student> expected = new ArrayList<>(List.of(new Student(1L, "Harry", 35), new Student(3L, "Thor", 35)));

        //Запуск теста
        Collection<Student> actual = cut.filter(35);
        assertArrayEquals(expected.toArray(), actual.toArray());
        verify(cutMock, times(1)).findAll();
    }

    @Test
    void testGetAll() {
        //Подготовка входных данных
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "Harry", 35));
        students.add(new Student(2L, "Rhon", 91));
        students.add(new Student(3L, "Thor", 35));
        students.add(new Student(4L, "Yoda", 23));
        when(cutMock.findAll()).thenReturn(students);

        //Подготовка ожидаемого результата
        List<Student> expected = new ArrayList<>(List.of(new Student(1L, "Harry", 35),
                new Student(2L, "Rhon", 91),
                new Student(3L, "Thor", 35),
                new Student(4L, "Yoda", 23)
                ));

        //Запуск теста
        Collection<Student> actual = cut.getAll();
        assertArrayEquals(expected.toArray(), actual.toArray());
        verify(cutMock, times(1)).findAll();
    }
}
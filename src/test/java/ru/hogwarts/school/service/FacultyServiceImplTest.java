package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @InjectMocks
    FacultyServiceImpl cut;

    @Mock
    FacultyRepository cutMock;

    @Test
    void testAdd() {
        //Подготовка входных данных
        when(cutMock.save(any())).thenReturn(new Faculty(1L, "Griffindor", "red-yellow"));
        Faculty faculty = new Faculty(0L, "Griffindor", "red-yellow");

        //Подготовка ожидаемого результата
        Faculty expected = new Faculty(1L, "Griffindor", "red-yellow");

        //Запуск теста
        Faculty actual = cut.add(faculty);
        assertEquals(expected, actual);
        verify(cutMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        //Подготовка входных данных
        when(cutMock.save(any())).thenReturn(new Faculty(1L, "Griffindor", "red"));
        when(cutMock.findById(any())).thenReturn(Optional.of(new Faculty(1L, "Griffindor", "red")));

        //Подготовка ожидаемого результата
        Faculty expected = new Faculty(1L, "Griffindor", "red");

        //Запуск теста
        Faculty actual = cut.update(1L, new Faculty(1L, "Griffindor", "red"));
        assertEquals(expected, actual);
        verify(cutMock, times(1)).save(any());
    }

    @Test
    void testFind() {
        //Подготовка входных данных
        when(cutMock.findById(any())).thenReturn(Optional.of(new Faculty(1L, "Griffindor", "red-yellow")));

        //Подготовка ожидаемого результата
        Faculty expected = new Faculty(1L, "Griffindor", "red-yellow");

        //Запуск теста
        Faculty actual = cut.find(1L);
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
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(1L, "Griffindor", "red-yellow"));
        faculties.add(new Faculty(2L, "Slizzerin", "green-white"));
        faculties.add(new Faculty(3L, "Jedi-shcool", "green-white"));
        faculties.add(new Faculty(4L, "Wall-Stret", "grey"));
        when(cutMock.findAll()).thenReturn(faculties);

        //Подготовка ожидаемого результата
        Collection<Faculty> expected = new ArrayList<>(List.of(new Faculty(2L, "Slizzerin", "green-white"), new Faculty(3L, "Jedi-shcool", "green-white")));

        //Запуск теста
        Collection<Faculty> actual = cut.filter("green-white");
        assertArrayEquals(expected.toArray(), actual.toArray());
        verify(cutMock, times(1)).findAll();
    }

    @Test
    void testGetAll() {
        //Подготовка входных данных
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(1L, "Griffindor", "red-yellow"));
        faculties.add(new Faculty(2L, "Slizzerin", "green-white"));
        faculties.add(new Faculty(3L, "Jedi-shcool", "green-white"));
        faculties.add(new Faculty(4L, "Wall-Stret", "grey"));
        when(cutMock.findAll()).thenReturn(faculties);

        //Подготовка ожидаемого результата
        Collection<Faculty> expected = new ArrayList<>(List.of(new Faculty(1L, "Griffindor", "red-yellow"),
                new Faculty(2L, "Slizzerin", "green-white"),
                new Faculty(3L, "Jedi-shcool", "green-white"),
                new Faculty(4L, "Wall-Stret", "grey")
                ));

        //Запуск теста
        Collection<Faculty> actual = cut.getAll();
        assertArrayEquals(expected.toArray(), actual.toArray());
        verify(cutMock, times(1)).findAll();
    }
}
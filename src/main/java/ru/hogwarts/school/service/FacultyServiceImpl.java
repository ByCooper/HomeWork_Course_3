package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    private Long generateId = 0L;

    @Override
    public Faculty add(Faculty faculty) {
        faculty.setId(++generateId);
        facultyMap.put(generateId, faculty);
        return faculty;
    }

    @Override
    public Faculty update(Faculty faculty) {
        facultyMap.get(faculty.getId()).setName(faculty.getName());
        facultyMap.get(faculty.getId()).setColor(faculty.getColor());
        return facultyMap.get(faculty.getId());
    }

    @Override
    public Faculty find(Long id) {
        return facultyMap.get(id);
    }

    @Override
    public Faculty remove(Long id) {
        facultyMap.remove(id);
        return facultyMap.get(id);
    }

    @Override
    public Collection<Faculty> filter(String color) {
        return facultyMap.values().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }
}

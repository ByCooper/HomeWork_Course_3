package ru.hogwarts.school.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByNameContainsIgnoreCase(String name);
    Collection<Faculty> findByColorContainsIgnoreCase(String color);
}

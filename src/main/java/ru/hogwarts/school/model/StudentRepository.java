package ru.hogwarts.school.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);
    List<Student> findByFaculty_Id(Long id);
    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getStudentByCategory();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Integer getStudentByAvgAge();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<StudentByCategory> getStudentByOffset();

}
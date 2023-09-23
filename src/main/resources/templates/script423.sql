SELECT s.name, s.age, f.name FROM student AS s
LEFT JOIN faculty AS f ON s.faculty_id = f.id;

SELECT s.name FROM student AS s
RIGHT JOIN avatar AS a ON s.id = a.student_id;
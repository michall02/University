package pl.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.home.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

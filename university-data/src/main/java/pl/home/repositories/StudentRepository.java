package pl.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.home.models.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastNameStartsWithIgnoreCase(String lastName);
}

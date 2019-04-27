package pl.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.home.models.University;

public interface UniversityRepository extends JpaRepository<University, Long> {
}

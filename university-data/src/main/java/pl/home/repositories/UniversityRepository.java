package pl.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.home.models.University;

public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query("select count(s) from Student s where s.university.id =:universityId")
    Long getNumOfStudentsForUniversity(@Param("universityId") Long universityId);
}

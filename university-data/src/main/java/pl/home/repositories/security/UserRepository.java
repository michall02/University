package pl.home.repositories.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.home.models.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(@Param("username") String username);
}

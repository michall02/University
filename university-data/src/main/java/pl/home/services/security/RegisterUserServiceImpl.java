package pl.home.services.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.home.models.security.User;
import pl.home.repositories.security.UserRepository;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterUserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(username));
        repository.save(user);
    }
}

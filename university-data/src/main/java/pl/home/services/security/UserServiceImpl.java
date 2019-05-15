package pl.home.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.home.models.security.User;
import pl.home.repositories.security.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        return new CustomUserService(user.getUsername(), user.getPassword(), true,true,
                true,true,user.getAuthorities());
    }
}

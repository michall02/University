package pl.home.services;

import org.springframework.stereotype.Service;
import pl.home.models.Student;
import pl.home.repositories.StudentRepository;

import java.util.List;

@Service
public class ShowAllStudentsServiceImpl implements ShowAllStudentsService {

    private final StudentRepository repository;

    public ShowAllStudentsServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @Override
    public List<Student> findByLastNameStartsWithIgnoreCase(String lastName) {
        return repository.findByLastNameStartsWithIgnoreCase(lastName);
    }
}

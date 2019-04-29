package pl.home.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.home.models.Student;
import pl.home.repositories.StudentRepository;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService {

    private final StudentRepository repository;

    public RemoveStudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void removeStudent(Student student) {
        repository.delete(student);
    }
}

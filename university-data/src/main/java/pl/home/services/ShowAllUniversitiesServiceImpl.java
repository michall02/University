package pl.home.services;

import org.springframework.stereotype.Service;
import pl.home.models.University;
import pl.home.repositories.UniversityRepository;

import java.util.List;

@Service
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService {

    private final UniversityRepository repository;

    public ShowAllUniversitiesServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }
}

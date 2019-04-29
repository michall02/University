package pl.home.services;

import org.springframework.stereotype.Service;
import pl.home.repositories.UniversityRepository;

@Service
public class UniversityStatsServiceImpl implements UniversityStatsService {

    private final UniversityRepository repository;

    public UniversityStatsServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long getNumOfStudentsForUniversity(Long universityId) {
        return repository.getNumOfStudentsForUniversity(universityId);
    }
}

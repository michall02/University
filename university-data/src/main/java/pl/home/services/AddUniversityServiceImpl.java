package pl.home.services;

import com.vaadin.ui.Notification;
import org.springframework.stereotype.Service;
import pl.home.models.University;
import pl.home.repositories.UniversityRepository;

import javax.validation.ConstraintViolationException;

import static pl.home.utils.NotificationMessages.UNIVERSITY_SAVE_VALIDATION_ACCEPTED_DESCRIPTION;
import static pl.home.utils.NotificationMessages.UNIVERSITY_SAVE_VALIDATION_ACCEPTED_TITLE;
import static pl.home.utils.NotificationMessages.UNIVERSITY_SAVE_VALIDATION_ERROR_DESCRIPTION;
import static pl.home.utils.NotificationMessages.UNIVERSITY_SAVE_VALIDATION_ERROR_TITLE;

@Service
public class AddUniversityServiceImpl implements AddUniversityService {

    private final UniversityRepository repository;

    public AddUniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addUniversity(University universityDAO) {
        University university = new University();
        university.setUniversityName(universityDAO.getUniversityName());
        university.setUniversityCountry(universityDAO.getUniversityCountry());
        university.setUniversityCity(universityDAO.getUniversityCity());

        try{
            repository.save(university);
            Notification.show(UNIVERSITY_SAVE_VALIDATION_ACCEPTED_TITLE.getValue(),
                    UNIVERSITY_SAVE_VALIDATION_ACCEPTED_DESCRIPTION.getValue(),
                    Notification.Type.HUMANIZED_MESSAGE);

        }catch(ConstraintViolationException e) {
            Notification.show(UNIVERSITY_SAVE_VALIDATION_ERROR_TITLE.getValue(),
                    UNIVERSITY_SAVE_VALIDATION_ERROR_DESCRIPTION.getValue(),
                    Notification.Type.ERROR_MESSAGE);

        }
    }
}

package pl.home.services;

import com.vaadin.ui.Notification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.home.models.Student;
import pl.home.repositories.StudentRepository;

import javax.validation.ConstraintViolationException;

import static pl.home.utils.NotificationMessages.STUDENT_SAVE_VALIDATION_ACCEPTED_DESCRIPTION;
import static pl.home.utils.NotificationMessages.STUDENT_SAVE_VALIDATION_ACCEPTED_TITLE;
import static pl.home.utils.NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION;
import static pl.home.utils.NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE;

@Service
public class AddStudentServiceImpl implements AddStudentService {

    private final StudentRepository repository;

    public AddStudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void saveStudent(Student studentDAO) {
        Student student = new Student();
        student.setFirstName(studentDAO.getFirstName());
        student.setLastName(studentDAO.getLastName());
        student.setAge(studentDAO.getAge());
        student.setGender(studentDAO.getGender());
        student.setUniversity(studentDAO.getUniversity());

        try {
            repository.save(student);

            Notification.show(STUDENT_SAVE_VALIDATION_ACCEPTED_TITLE.getValue(),
                    STUDENT_SAVE_VALIDATION_ACCEPTED_DESCRIPTION.getValue(),
                    Notification.Type.HUMANIZED_MESSAGE);

        } catch (ConstraintViolationException e) {
            Notification.show(STUDENT_SAVE_VALIDATION_ERROR_TITLE.getValue(),
                    STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getValue(),
                    Notification.Type.ERROR_MESSAGE);

        }
    }
}

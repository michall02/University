package pl.home.ui.students;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import pl.home.models.Student;
import pl.home.models.University;
import pl.home.services.AddStudentService;
import pl.home.services.ShowAllUniversitiesService;
import pl.home.ui.commons.UIComponentBuilderWithListener;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static pl.home.models.Gender.FEMALE;
import static pl.home.models.Gender.MALE;
import static pl.home.models.Gender.OTHER;
import static pl.home.utils.NotificationMessages.STUDENT_SAVE_INVALID_DESCRIPTION;
import static pl.home.utils.NotificationMessages.STUDENT_SAVE_INVALID_TITLE;
import static pl.home.utils.StudentUtils.AGE;
import static pl.home.utils.StudentUtils.CLEAR;
import static pl.home.utils.StudentUtils.FIRST_NAME;
import static pl.home.utils.StudentUtils.GENDER;
import static pl.home.utils.StudentUtils.LAST_NAME;
import static pl.home.utils.StudentUtils.SAVE;
import static pl.home.utils.StudentUtils.UNIVERSITY;



@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory implements UIComponentBuilderWithListener {


    private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener {
        private TextField firstName;
        private TextField lastName;
        private TextField age;
        private ComboBox<String> gender;
        private ComboBox<University> universityComboBox;
        private Button saveBtn;
        private Button clearBtn;

        private Binder<Student> binderGroup;
        private Student student;

        private SavedListener savedListener;

        public AddStudentMainLayout(SavedListener savedListener) {
            this.savedListener = savedListener;
        }

        public AddStudentMainLayout init() {
            binderGroup = new Binder<>(Student.class);
            student = new Student();

            firstName = new TextField(FIRST_NAME.getValue());
            lastName = new TextField(LAST_NAME.getValue());
            age = new TextField(AGE.getValue());
            gender = new ComboBox<>(GENDER.getValue());
            gender.setItems(MALE.getValue(), FEMALE.getValue(), OTHER.getValue());

            saveBtn = new Button(SAVE.getValue());
            saveBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            universityComboBox = new ComboBox<>(UNIVERSITY.getValue());
            universityComboBox.setWidth("100%");

            clearBtn = new Button(CLEAR.getValue());
            clearBtn.setStyleName(ValoTheme.BUTTON_DANGER);

            saveBtn.addClickListener(this);
            clearBtn.addClickListener(this);

            return this;
        }

        public AddStudentMainLayout bind() {
            binderGroup.bindInstanceFields(this);
            binderGroup.setBean(student);
            return this;
        }

        public AddStudentMainLayout load() {
            List<University> universities = showAllUniversitiesService.getAllUniversities();
            universityComboBox.setItems(universities);
            return this;
        }

        public Component layout() {
            setMargin(true);


            GridLayout gridLayout = new GridLayout(4, 6);
            gridLayout.setSizeUndefined();
            gridLayout.setSpacing(true);
            gridLayout.addComponent(firstName, 2, 2);
            gridLayout.addComponent(lastName, 3, 2);
            gridLayout.addComponent(age, 2, 3);
            gridLayout.addComponent(gender, 3, 3);
            gridLayout.addComponent(universityComboBox, 2, 4, 3, 4);
            gridLayout.addComponent(new HorizontalLayout(saveBtn, clearBtn), 2, 5);


            return gridLayout;
        }


        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            if (clickEvent.getSource() == this.saveBtn) {
                save();
            } else {
                clearField();
            }
        }

        private boolean isSaveOperationValide() {
            return showAllUniversitiesService.getAllUniversities().size() != 0;
        }

        private void save() throws ConstraintViolationException {
            if (!isSaveOperationValide() || universityComboBox.isEmpty() ) {
                Notification.show(STUDENT_SAVE_INVALID_TITLE.getValue(),
                        STUDENT_SAVE_INVALID_DESCRIPTION.getValue(),
                        Notification.Type.WARNING_MESSAGE);
                return;
            }
            binderGroup.writeBeanIfValid(student);
            addStudentService.saveStudent(student);
            savedListener.saved();
            clearField();
        }

        private void clearField() {
            firstName.setValue("");
            lastName.setValue("");
            age.setValue("");
            gender.setValue("");
        }
    }

    private final AddStudentService addStudentService;
    private final ShowAllUniversitiesService showAllUniversitiesService;

    public AddStudentMainLayoutFactory(AddStudentService addStudentService, ShowAllUniversitiesService showAllUniversitiesService) {
        this.addStudentService = addStudentService;
        this.showAllUniversitiesService = showAllUniversitiesService;
    }

    public Component createComponent(SavedListener savedListener) {
        return new AddStudentMainLayout(savedListener).init().load().bind().layout();
    }
}

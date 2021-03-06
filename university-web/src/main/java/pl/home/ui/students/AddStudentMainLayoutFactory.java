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
import pl.home.i18helper.I18Helper;
import pl.home.models.Student;
import pl.home.models.University;
import pl.home.services.AddStudentService;
import pl.home.services.ShowAllUniversitiesService;
import pl.home.ui.commons.UIComponentBuilderWithListener;
import pl.home.ui.commons.UniversityMainUI;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;

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
        private I18Helper i18Helper;

        private SavedListener savedListener;

        public AddStudentMainLayout(SavedListener savedListener) {
            this.savedListener = savedListener;
        }

        public AddStudentMainLayout init() {
            Locale locale = new Locale(UniversityMainUI.LOCALE);
            i18Helper = new I18Helper(locale);

            binderGroup = new Binder<>(Student.class);
            student = new Student();

            firstName = new TextField(i18Helper.getMessage("student.firstName"));
            lastName = new TextField(i18Helper.getMessage("student.lastName"));
            age = new TextField(i18Helper.getMessage("student.age"));
            gender = new ComboBox<>(i18Helper.getMessage("student.gender"));
            gender.setItems(i18Helper.getMessage("gender.male"), i18Helper.getMessage("gender.female"), i18Helper.getMessage("gender.other"));

            saveBtn = new Button(i18Helper.getMessage("button.save"));
            saveBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            universityComboBox = new ComboBox<>(i18Helper.getMessage("student.university"));
            universityComboBox.setWidth("100%");

            clearBtn = new Button(i18Helper.getMessage("button.clear"));
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

        private void save() throws ConstraintViolationException {
            if (universityComboBox.isEmpty() ) {
                Notification.show(i18Helper.getMessage("msg.errorTitle"),
                        i18Helper.getMessage("msg.savedInvalidDescription"),
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

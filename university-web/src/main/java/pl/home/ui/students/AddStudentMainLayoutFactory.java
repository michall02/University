package pl.home.ui.students;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import pl.home.models.Student;
import pl.home.ui.commons.UIComponentBuilder;

import static pl.home.models.Gender.FEMALE;
import static pl.home.models.Gender.MALE;
import static pl.home.utils.StudentUtils.AGE;
import static pl.home.utils.StudentUtils.CLEAR;
import static pl.home.utils.StudentUtils.FIRST_NAME;
import static pl.home.utils.StudentUtils.GENDER;
import static pl.home.utils.StudentUtils.LAST_NAME;
import static pl.home.utils.StudentUtils.SAVE;


@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory implements UIComponentBuilder {


    private class AddStudentMainLayout extends VerticalLayout {
        private TextField firstName;
        private TextField lastName;
        private TextField age;
        private ComboBox<String> gender;
        private Button saveBtn;
        private Button clearBtn;

        private Binder<Student> binderGroup;
        private Student student;

        public AddStudentMainLayout init() {
            binderGroup = new Binder<>(Student.class);
            student = new Student();

            firstName = new TextField(FIRST_NAME.getValue());
            lastName = new TextField(LAST_NAME.getValue());
            age = new TextField(AGE.getValue());
            gender = new ComboBox<>(GENDER.getValue());

            saveBtn = new Button(SAVE.getValue());
            clearBtn = new Button(CLEAR.getValue());

            gender.setItems(MALE.getValue(), FEMALE.getValue());
            return this;
        }

        public AddStudentMainLayout bind() {
//            binderGroup.bind(firstName, Student::getFirstName, Student::setFirstName);
//            binderGroup.bind(lastName, Student::getLastName, Student::setLastName);
//            binderGroup.forField(age)
//                    .withConverter(new StringToIntegerConverter(String.valueOf(age)))
//                    .bind(Student::getAge,Student::setAge);
//            binderGroup.bind(gender, Student::getGender, Student::setGender);
            binderGroup.bindInstanceFields(this);
            binderGroup.setBean(student);
            return this;
        }

        public Component layout() {
            setMargin(true);

            GridLayout gridLayout = new GridLayout(2, 3);
            gridLayout.setSizeUndefined();
            gridLayout.setSpacing(true);
            gridLayout.addComponent(firstName, 0, 0);
            gridLayout.addComponent(lastName, 1, 0);
            gridLayout.addComponent(age, 0, 1);
            gridLayout.addComponent(gender, 1, 1);
            gridLayout.addComponent(new HorizontalLayout(saveBtn, clearBtn), 0, 2);

            return gridLayout;
        }
    }

    public Component createComponent() {
        return new AddStudentMainLayout().init().bind().layout();
    }
}

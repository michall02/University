package pl.home.ui.universities;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import pl.home.i18helper.I18Helper;
import pl.home.models.University;
import pl.home.services.AddUniversityService;
import pl.home.ui.commons.UIComponentBuilderWithListener;
import pl.home.ui.commons.UniversityMainUI;
import pl.home.ui.students.SavedListener;

import javax.validation.ConstraintViolationException;

import java.util.Locale;

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory implements UIComponentBuilderWithListener {


    private class AddUniversityLayout extends VerticalLayout implements Button.ClickListener{

        private TextField universityName;
        private TextField universityCountry;
        private TextField universityCity;
        private Button saveBtn;
        private University university;
        private Binder<University> binderGroup;

        private I18Helper i18Helper;
        private Locale locale;

        private SavedListener savedListener;

        public AddUniversityLayout(SavedListener savedListener) {
            this.savedListener = savedListener;
        }

        public AddUniversityLayout init(){
            locale = new Locale(UniversityMainUI.LOCALE);
            i18Helper = new I18Helper(locale);

            binderGroup = new Binder<>(University.class);
            university = new University();

            universityName = new TextField(i18Helper.getMessage("university.name"));
            universityCountry = new TextField(i18Helper.getMessage("university.country"));
            universityCity = new TextField(i18Helper.getMessage("university.city"));

            saveBtn = new Button(i18Helper.getMessage("button.save"));
            saveBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            saveBtn.addClickListener(this);

            return this;
        }

        public AddUniversityLayout bind(){
            binderGroup.bindInstanceFields(this);
            binderGroup.setBean(university);
            return this;
        }

        public Component layout(){
            setMargin(true);
            setWidth("100%");
            GridLayout gridLayout = new GridLayout(3, 6);
            gridLayout.setSizeUndefined();
            gridLayout.setSpacing(true);
            gridLayout.addComponent(universityName, 2, 2);
            gridLayout.addComponent(universityCountry, 2, 3);
            gridLayout.addComponent(universityCity, 2, 4);
            gridLayout.addComponent(saveBtn, 2, 5);


            return gridLayout;
        }

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) throws ConstraintViolationException {
            binderGroup.writeBeanIfValid(university);
            addUniversityService.addUniversity(university);
            savedListener.saved();

            clearFields();
        }

        private void clearFields(){
            universityName.setValue("");
            universityCountry.setValue("");
            universityCity.setValue("");
        }


    }

    private final AddUniversityService addUniversityService;

    public AddUniversityLayoutFactory(AddUniversityService addUniversityService) {
        this.addUniversityService = addUniversityService;
    }

    public Component createComponent(SavedListener savedListener){
        return new AddUniversityLayout(savedListener).init().bind().layout();
    }
}

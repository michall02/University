package pl.home.ui.students;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import pl.home.ui.commons.UniversityMainUI;

@SpringView(name = StudentLayoutFactory.NAME, ui = UniversityMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View {
    public static final String NAME = "addstudent";

    private TabSheet tabSheet;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();

    }

    private void addLayout(){
        setMargin(true);
        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");
        Component addStudentMainTab = new Label("First tab content..");
    }
}

package pl.home.ui.students;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.ui.commons.UIComponentBuilder;
import pl.home.ui.commons.UniversityMainUI;

import static pl.home.utils.StudentUtils.MAIN_MENU;
import static pl.home.utils.StudentUtils.SHOW_ALL_STUDENTS;

@SpringView(name = StudentLayoutFactory.NAME, ui = UniversityMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View {
    public static final String NAME = "addstudent";

    private final UIComponentBuilder addStudentComponentBuilder;

    private TabSheet tabSheet;

    public StudentLayoutFactory(@Qualifier("addStudentMainLayoutFactory") UIComponentBuilder addStudentComponentBuilder) {
        this.addStudentComponentBuilder = addStudentComponentBuilder;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        addLayout();

    }

    private void addLayout(){
        setMargin(true);
        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");
        Component addStudentTab = addStudentComponentBuilder.createComponent();
        Component showStudentTab = new Label("Show student tab content..");

        tabSheet.addTab(addStudentTab, MAIN_MENU.getValue());
        tabSheet.addTab(showStudentTab, SHOW_ALL_STUDENTS.getValue());

        addComponent(tabSheet);
    }
}

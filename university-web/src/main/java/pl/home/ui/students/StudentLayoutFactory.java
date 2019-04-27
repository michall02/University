package pl.home.ui.students;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.ui.commons.UIComponentBuilder;
import pl.home.ui.commons.UIComponentBuilderWithListener;
import pl.home.ui.commons.UniversityMainUI;

import static pl.home.utils.StudentUtils.MAIN_MENU;
import static pl.home.utils.StudentUtils.SHOW_ALL_STUDENTS;

@SpringView(name = StudentLayoutFactory.NAME, ui = UniversityMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View, StudentSavedListener {
    public static final String NAME = "addstudent";

    private TabSheet tabSheet;

    private final UIComponentBuilderWithListener addStudentComponentBuilder;
    private final UIComponentBuilder showAllStudentsComponentBuilder;
    private final UITableRefresher showAllStudentsRefreshTable;


    public StudentLayoutFactory(UIComponentBuilderWithListener addStudentComponentBuilder,
                                @Qualifier("showAllStudentsLayoutFactory") UIComponentBuilder showAllStudentsComponentBuilder, UITableRefresher showAllStudentsRefreshTable) {
        this.addStudentComponentBuilder = addStudentComponentBuilder;
        this.showAllStudentsComponentBuilder = showAllStudentsComponentBuilder;
        this.showAllStudentsRefreshTable = showAllStudentsRefreshTable;
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
        Component addStudentTab = addStudentComponentBuilder.createComponent(this);
        Component showStudentTab = showAllStudentsComponentBuilder.createComponent();

        tabSheet.addTab(addStudentTab, MAIN_MENU.getValue());
        tabSheet.addTab(showStudentTab, SHOW_ALL_STUDENTS.getValue());

        addComponent(tabSheet);
    }

    @Override
    public void studentSaved() {
        showAllStudentsRefreshTable.refreshTable();
    }
}

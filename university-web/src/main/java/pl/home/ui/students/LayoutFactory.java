package pl.home.ui.students;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.i18helper.I18Helper;
import pl.home.ui.commons.UIComponentBuilder;
import pl.home.ui.commons.UIComponentBuilderWithListener;
import pl.home.ui.commons.UniversityMainUI;

import java.util.Locale;

import static pl.home.utils.StudentUtils.MAIN_MENU;
import static pl.home.utils.StudentUtils.SHOW_ALL_STUDENTS;

@SpringView(name = LayoutFactory.NAME, ui = UniversityMainUI.class)
public class LayoutFactory extends VerticalLayout implements View, SavedListener {
    public static final String NAME = "addstudent" ;

    private final UIComponentBuilderWithListener addStudentComponentBuilder;
    private final UIComponentBuilder showAllStudentsComponentBuilder;
    private final UITableRefresher showAllStudentsRefreshTable;

    public LayoutFactory(@Qualifier("addStudentMainLayoutFactory") UIComponentBuilderWithListener addStudentComponentBuilder,
                         @Qualifier("showAllStudentsLayoutFactory") UIComponentBuilder showAllStudentsComponentBuilder,
                         @Qualifier("showAllStudentsLayoutFactory") UITableRefresher showAllStudentsRefreshTable) {
        this.addStudentComponentBuilder = addStudentComponentBuilder;
        this.showAllStudentsComponentBuilder = showAllStudentsComponentBuilder;
        this.showAllStudentsRefreshTable = showAllStudentsRefreshTable;
    }

    private TabSheet tabSheet;
    Locale locale = new Locale("pl");
    I18Helper i18Helper = new I18Helper(locale);

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

        tabSheet.addTab(addStudentTab, i18Helper.getMessage("menu.registration"));
        tabSheet.addTab(showStudentTab, i18Helper.getMessage("menu.allStudents"));

        addComponent(tabSheet);
    }

    @Override
    public void saved() {
        showAllStudentsRefreshTable.refreshTable();
    }
}

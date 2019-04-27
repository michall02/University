package pl.home.ui.students;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.themes.ValoTheme;
import pl.home.models.Student;
import pl.home.services.RemoveStudentService;
import pl.home.services.ShowAllStudentsService;
import pl.home.ui.commons.UniversityMainUI;
import pl.home.utils.StudentUtils;

import java.util.List;

import static pl.home.utils.NotificationMessages.STUDENT_REMOVE_SUCCESS_DESCRIPTION;
import static pl.home.utils.NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE;

@SpringView(name = RemoveStudentLayoutFactory.NAME, ui = UniversityMainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener {
    public static final String NAME = "removestudent";

    private Grid<Student> removeStudentTable;
    private Button removeStudentBtn;
    private List<Student> students;
    private ListDataProvider<Student> listProvider;

    private TabSheet tabSheet;

    private final ShowAllStudentsService showAllStudentsService;
    private final RemoveStudentService removeStudentService;

    public RemoveStudentLayoutFactory(ShowAllStudentsService showAllStudentsService, RemoveStudentService removeStudentService) {
        this.showAllStudentsService = showAllStudentsService;
        this.removeStudentService = removeStudentService;
    }

    public void addLayout(){
        setMargin(true);
        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");
        VerticalLayout removeTab = new VerticalLayout();
        removeTab.setMargin(true);

        removeStudentBtn = new Button("Remove");
        removeStudentBtn.setStyleName(ValoTheme.BUTTON_DANGER);
        listProvider = new ListDataProvider<>(students);

        removeStudentTable = new Grid<>(Student.class);
        removeStudentTable.setDataProvider(listProvider);
        removeStudentTable.setColumns("firstName", "lastName", "age", "gender");
        removeStudentTable.setSelectionMode(Grid.SelectionMode.MULTI);

        removeStudentBtn.addClickListener(this);

        removeTab.addComponents(removeStudentTable, removeStudentBtn);

        tabSheet.addTab(removeTab, StudentUtils.REMOVE_MENU.getValue());

        addComponent(tabSheet);



//        addComponent(removeStudentTable);
//        addComponent(removeStudentBtn);

    }

    private void loadStudents() {
        students = showAllStudentsService.getAllStudent();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        MultiSelectionModel selectionModel = (MultiSelectionModel) removeStudentTable.getSelectionModel();

        for (Object selectedItem : selectionModel.getSelectedItems()) {
            Student student = (Student) selectedItem;
            listProvider.getItems().remove(student);
            removeStudentService.removeStudent(student);
            removeStudentTable.getDataProvider().refreshAll();
        }
        Notification.show(STUDENT_REMOVE_SUCCESS_TITLE.getValue(),
                STUDENT_REMOVE_SUCCESS_DESCRIPTION.getValue(),
                Notification.Type.WARNING_MESSAGE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if(removeStudentTable  != null){
            return;
        }

        loadStudents();
        addLayout();
    }



}

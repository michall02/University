package pl.home.ui.students;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.sass.internal.util.StringUtil;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.util.StringUtils;
import pl.home.models.Student;
import pl.home.services.ShowAllStudentsService;
import pl.home.ui.commons.UIComponentBuilder;

import java.util.List;

@org.springframework.stereotype.Component
public class ShowAllStudentsLayoutFactory implements UIComponentBuilder, UITableRefresher {

    private List<Student> students;
    private ListDataProvider<Student> listProvider;
    private Grid<Student> studentTable;
    private TextField filter;

    private class ShowAllStudentsLayout extends VerticalLayout {

        public ShowAllStudentsLayout init(){
            setMargin(true);

            listProvider = new ListDataProvider<>(students);
            studentTable = new Grid<>(Student.class);
            studentTable.setDataProvider(listProvider);
            studentTable.setColumns("firstName", "lastName", "age", "gender");

            filter = new TextField();
            filter.setPlaceholder("Filter by last name...");
            filter.setValueChangeMode(ValueChangeMode.EAGER);
            filter.addValueChangeListener(e -> updateList());

            return this;
        }

        public ShowAllStudentsLayout load(){
            students = showAllStudentsService.getAllStudent();
            return this;
        }

        public ShowAllStudentsLayout layout(){
            VerticalLayout verticalLayout = new VerticalLayout(filter,studentTable);
            addComponent(verticalLayout);
            return this;
        }
    }

    private void updateList() {
        studentTable.setItems(showAllStudentsService.findByLastNameStartsWithIgnoreCase(filter.getValue()));
    }

    @Override
    public void refreshTable() {
        students = showAllStudentsService.getAllStudent();
        listProvider.getItems().removeIf(student -> !student.getFirstName().isEmpty());
        listProvider.getItems().addAll(students);
    }

    private final ShowAllStudentsService showAllStudentsService;

    public ShowAllStudentsLayoutFactory(ShowAllStudentsService showAllStudentsService) {
        this.showAllStudentsService = showAllStudentsService;
    }

    @Override
    public Component createComponent() {
        return new ShowAllStudentsLayout().load().init().layout();
    }


}

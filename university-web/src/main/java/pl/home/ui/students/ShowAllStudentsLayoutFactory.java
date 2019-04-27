package pl.home.ui.students;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import pl.home.models.Student;
import pl.home.services.ShowAllStudentsService;
import pl.home.ui.commons.UIComponentBuilder;

import java.util.List;

@org.springframework.stereotype.Component
public class ShowAllStudentsLayoutFactory implements UIComponentBuilder, UITableRefresher {

    private List<Student> students;
    private ListDataProvider<Student> listProvider;
    private Grid<Student> studentTable;

    private class ShowAllStudentsLayout extends VerticalLayout {

        public ShowAllStudentsLayout init(){
            setMargin(true);
            listProvider = new ListDataProvider<>(students);
            studentTable = new Grid<>(Student.class);
            studentTable.setDataProvider(listProvider);
            studentTable.setColumns("firstName", "lastName", "age", "gender");


            return this;
        }

        public ShowAllStudentsLayout load(){
            students = showAllStudentsService.getAllStudent();
            return this;
        }

        public ShowAllStudentsLayout layout(){
            addComponent(studentTable);
            return this;
        }
    }

    @Override
    public void refreshTable() {
        students = showAllStudentsService.getAllStudent();
        listProvider.getItems().removeAll(students);
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

package pl.home.ui.students;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import pl.home.models.Student;
import pl.home.ui.commons.UIComponentBuilder;

import java.util.ArrayList;
import java.util.List;

public class ShowAllStudentsLayoutFactory implements UIComponentBuilder {

    //List<Book> data = new Arraylist<>(getBooks());
    //ListDataProvider<Book> dataProvider = new ListDataProvider<>(data);
    //Grid<Book> grid = new Grid<>();
    //grid.setDataProvider(dataProvider);

    private List<Student> students;
    private ListDataProvider<Student> listProvider;
    private Grid<Student> studentTable;

    private class ShowAllStudentsLayout extends VerticalLayout {

        public ShowAllStudentsLayout init(){
            setMargin(true);
            students = new ArrayList<>();
            listProvider = new ListDataProvider<>(students);
            studentTable = new Grid<>();
            studentTable.setDataProvider(listProvider);
            studentTable.setColumnOrder("firstName", "lastName", "age", "gender");
            studentTable.removeColumn("id");

            return this;
        }

        public ShowAllStudentsLayout load(){

            return this;
        }

        public ShowAllStudentsLayout layout(){
            addComponent(studentTable);
            return this;
        }
    }



    @Override
    public Component createComponent() {
        return new ShowAllStudentsLayout().init().load().layout();
    }
}

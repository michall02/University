package pl.home.ui.universities;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import pl.home.models.University;
import pl.home.services.ShowAllUniversitiesService;
import pl.home.ui.commons.UIComponentBuilder;
import pl.home.ui.students.UITableRefresher;

import java.util.List;

@org.springframework.stereotype.Component
public class ShowAllUniversitiesLayoutFactory implements UIComponentBuilder, UITableRefresher {

    private List<University> universities;
    private ListDataProvider<University> listProvider;
    private Grid<University> universitiesTable;

    private class ShowAllUniversitiesLayout extends VerticalLayout {

        public ShowAllUniversitiesLayout init(){
            setMargin(true);
            listProvider = new ListDataProvider<>(universities);
            universitiesTable = new Grid<>(University.class);
            universitiesTable.setDataProvider(listProvider);
            universitiesTable.setColumns("universityName","universityCountry","universityCity");
            universitiesTable.setSizeFull();

            return this;
        }

        public ShowAllUniversitiesLayout load(){
            universities = showAllUniversitiesService.getAllUniversities();
            return this;
        }

        public ShowAllUniversitiesLayout layout(){
            addComponent(universitiesTable);
            return this;
        }
    }


    @Override
    public void refreshTable() {
        universities = showAllUniversitiesService.getAllUniversities();
        listProvider.getItems().removeIf(university -> !university.getUniversityName().isEmpty());
        listProvider.getItems().addAll(universities);
    }

    private final ShowAllUniversitiesService showAllUniversitiesService;

    public ShowAllUniversitiesLayoutFactory(ShowAllUniversitiesService showAllUniversitiesService) {
        this.showAllUniversitiesService = showAllUniversitiesService;
    }

    @Override
    public Component createComponent() {
        return new ShowAllUniversitiesLayout().load().init().layout();
    }
}

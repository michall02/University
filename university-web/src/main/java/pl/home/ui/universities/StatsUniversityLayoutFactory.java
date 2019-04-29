package pl.home.ui.universities;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import pl.home.models.University;
import pl.home.services.ShowAllUniversitiesService;
import pl.home.services.UniversityStatsService;
import pl.home.ui.commons.UIComponentBuilder;

import java.util.List;

@org.springframework.stereotype.Component
public class StatsUniversityLayoutFactory implements UIComponentBuilder {

    private List<University> universities;

    private final ShowAllUniversitiesService showAllUniversitiesService;
    private final UniversityStatsService universityStatsService;

    public StatsUniversityLayoutFactory(ShowAllUniversitiesService showAllUniversitiesService, UniversityStatsService universityStatsService) {
        this.showAllUniversitiesService = showAllUniversitiesService;
        this.universityStatsService = universityStatsService;
    }

    private class StatsUniversityLayout extends VerticalLayout {

        public StatsUniversityLayout load(){
            universities = showAllUniversitiesService.getAllUniversities();
            return this;
        }

        public StatsUniversityLayout layout(){
            setMargin(true);
            for (University university : universities) {
                Long numOfStudents = universityStatsService.getNumOfStudentsForUniversity(university.getId());
                String statement;
                if(numOfStudents == 1){
                    statement = "<p><b>"+university.getUniversityName()+"</b>"+" - "+numOfStudents+" student"+"</p>";
                }else{
                    statement = "<p><b>"+university.getUniversityName()+"</b>"+" - "+numOfStudents+" students"+"</p>";
                }
                Label label = new Label(statement, ContentMode.HTML);
                addComponent(label);
            }
            return this;
        }


    }

    @Override
    public Component createComponent() {
        return new StatsUniversityLayout().load().layout();
    }
}
